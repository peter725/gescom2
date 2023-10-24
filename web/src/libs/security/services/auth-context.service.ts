import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { AuthSubject, UnauthenticatedSubject } from '../auth-subject';


@Injectable({ providedIn: 'root' })
export class AuthContextService<T extends AuthSubject<unknown> = AuthSubject<never>> {
  private readonly _subjectStore = new BehaviorSubject<T>(new UnauthenticatedSubject() as unknown as T);

  private readonly _authWillExpireIn = new Subject<number>();
  private readonly _authHasExpired = new Subject<void>();

  private _notifyExpiredTimeout: any;
  private _notifyExpireInTimeout: any;

  authorize(subject: T): void {
    this._subjectStore.next(subject);
    this.monitorAuthExp();
  }

  get(): Observable<T> {
    return this._subjectStore.asObservable();
  }

  instant(): T {
    return this._subjectStore.getValue();
  }

  clear(): void {
    this._subjectStore.next(new UnauthenticatedSubject() as unknown as T);
  }

  authWillExpireIn() {
    return this._authWillExpireIn.asObservable();
  }

  authHasExpired() {
    return this._authHasExpired.asObservable();
  }

  private monitorAuthExp() {
    if (this._notifyExpireInTimeout) clearTimeout(this._notifyExpireInTimeout);
    if (this._notifyExpiredTimeout) clearTimeout(this._notifyExpiredTimeout);

    const exp = this._subjectStore.value.getExpiresAt();
    const notificationTimeout = 10 * 60_000; // 10 minutes
    const ttl = exp - Date.now();
    const notifyTTL = ttl - notificationTimeout;

    this._notifyExpireInTimeout = setTimeout(
      () => {
        const updatedTTL = exp - Date.now();
        this._authWillExpireIn.next(updatedTTL);
      },
      notifyTTL
    );

    this._notifyExpiredTimeout = setTimeout(
      () => this._authHasExpired.next(),
      ttl
    );
  }
}
