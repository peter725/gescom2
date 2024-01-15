import { AuthUserDetails } from '@libs/sdk/auth';
import { AuthSubject } from '@libs/security';


export class AppAuthSubject extends AuthSubject<AuthUserDetails> {
  /**
   * Time when the authentication will expire at in milliseconds
   */
  private readonly expiresAt: number;

  constructor(
    /**
     * Current user details
     */
    details: AuthUserDetails,
    /**
     * Time when the authentication will expire at in seconds
     */
    expiresAt: number,
  ) {
    super(details);
    this.expiresAt = expiresAt;
    // this.expiresAt = expiresAt * 1000;
  }

  equals(value: AuthUserDetails | number | null | undefined): boolean {
    if (value == null) return false;

    const { userId } = this.getDetails();

    if (typeof value === 'number') {
      return userId === value;
    }

    return value.userId === userId;
  }

  getRoles(): string[] {
    return this.getDetails().roles || [];
  }

  getProfile(): string {
    return this.getDetails().profile || '';
  }

  isAuthenticated(): boolean {
    return !!this.getDetails();
  }

  getExpiresAt(): number {
    return this.expiresAt;
  }
}
