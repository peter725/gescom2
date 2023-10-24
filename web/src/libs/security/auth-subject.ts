import { ACLCheckMode } from './models';

export abstract class AuthSubject<T> {
  protected constructor(private readonly details: T) {
  }

  getDetails(): T {
    return this.details;
  }

  /**
   * Checks if the current subject has the required role or roles.
   * Depending on the check mode, the current subject must have all requested
   * roles or have one of the required roles.
   */
  hasRole(roles: string[] | string, check: ACLCheckMode = 'ALL'): boolean {
    const checkList = Array.isArray(roles) ? roles : [roles];
    switch (check) {
      case 'ALL':
        return checkList.every(r => this.getRoles().includes(r));
      default:
        return checkList.some(r => this.getRoles().includes(r));
    }
  }

  /**
   * Returns if the current subject is authenticated
   */
  abstract isAuthenticated(): boolean;

  /**
   * Returns the time of expiry of the current subject
   * expressed in milliseconds
   */
  abstract getExpiresAt(): number;

  /**
   * Returns a list of authorities of the current subject
   */
  abstract getRoles(): string[];

  /**
   * Returns a profile code of the current subject
   */
  abstract getProfile(): string;

  /**
   * Checks if a given value is the same as the current subject
   */
  abstract equals(value: unknown): boolean;
}

export class UnauthenticatedSubject extends AuthSubject<Record<string, unknown>> {
  constructor() {
    super({});
  }

  isAuthenticated() {
    return false;
  }

  getRoles(): string[] {
    return [];
  }

  getProfile(): string {
    return "";
  }

  getSurnames(): string {
    return "";
  }

  getExpiresAt(): number {
    return 0;
  }

  equals(): boolean {
    return false;
  }
}
