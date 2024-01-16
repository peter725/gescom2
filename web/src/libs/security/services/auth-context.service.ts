import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthSubject, UnauthenticatedSubject } from '@libs/security';


@Injectable({ providedIn: 'root' })
export class AuthContextService <T = AuthSubject<never>> {
  private readonly subjectStore = new BehaviorSubject<T>(new UnauthenticatedSubject() as unknown as T);

  authorize(subject: T): void {
    this.subjectStore.next(subject);
  }

  get(): Observable<T> {
    return this.subjectStore.asObservable();
  }

  instant(): T {
    return this.subjectStore.getValue();
  }

  clear(): void {
    this.subjectStore.next(new UnauthenticatedSubject() as unknown as T);
  }
}
