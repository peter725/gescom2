import {AuthDataResponse,} from '@libs/sdk/auth';

export const USER_AUTH_KEY = 'auth';
export const USER_SESSION = 'user';

export class AuthStorage {

  static saveUserAuth(data: AuthDataResponse) {
    this.saveItem(USER_AUTH_KEY, { ...data });
  }

  static saveUserSession(data: any){
    this.saveItem(USER_SESSION, { ...data });
  }

  static getUserAuth(): AuthDataResponse | undefined {
    return this.getItem<AuthDataResponse>(USER_AUTH_KEY);
  }

  public static getUserSession(): any | undefined {
    return this.getItem<any>(USER_SESSION);
  }

  static clearUserAuth() {
    localStorage.removeItem(USER_AUTH_KEY);
    localStorage.removeItem(USER_SESSION);
  }

  private static saveItem(key: string, data: Record<string, unknown>): void {
    try {
      const payload = JSON.stringify(data);
      localStorage.setItem(key, btoa(payload));
    } catch (e) {
      console.error(e);
    }
  }

  private static getItem<T>(key: string): T | undefined {
    try {
      const encodedStr = localStorage.getItem(key);
      if (!encodedStr) return undefined;
      const decodedStr = atob(encodedStr);
      const data = JSON.parse(decodedStr);
      return data as T;
    } catch (e) {
      console.error(e);
      return undefined;
    }
  }

}
