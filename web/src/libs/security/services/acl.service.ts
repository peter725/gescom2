import { Inject, Injectable } from '@angular/core';
import { AuthSubject, UnauthenticatedSubject } from '../auth-subject';
import { ACL, ResourceAccessKey } from '../models';
import { ACL_TOKEN } from '../tokens';
import { AuthContextService } from './auth-context.service';


@Injectable({ providedIn: 'root' })
export class AclService {
  private cache = new Map<ResourceAccessKey, boolean>();
  private previousSubject: AuthSubject<unknown>;

  constructor(
    private authContext: AuthContextService,
    @Inject(ACL_TOKEN) private acl: Promise<ACL>
  ) {
    this.previousSubject = authContext.instant();
    this.monitorAuthContext();
  }

  /**
   * Checks if the authenticated user can access the specified resource.
   */
  async canAccess(resource: ResourceAccessKey): Promise<boolean> {
    try {
      const acl = await this.acl;

      const condition = acl.get(resource);
      if (!condition) return false;

      if (this.cache.has(resource)) return !!this.cache.get(resource);

      const subject = this.authContext.instant();
      const canAccess = subject.hasRole(condition.roles, condition.check);
      this.cache.set(resource, canAccess);
      return canAccess;
    } catch (e) {
      return false;
    }
  }


  private monitorAuthContext() {
    this.authContext.get().subscribe({
      next: sub => this.authContextChanged(sub),
      error: () => this.authContextChanged(new UnauthenticatedSubject())
    });
  }

  private authContextChanged(subject: AuthSubject<unknown>) {
    const notSame = !(this.previousSubject.equals(subject.getDetails()));
    if (notSame) this.cache.clear();
  }
}
