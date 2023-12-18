import { AuthDataResponse, UserSignInPetition } from '@libs/sdk/auth';
import { TempAuthData } from './models';


export const USER_AUTH_KEY = 'auth';
export const TEMP_AUTH_KEY = 'tmp_auth';

export class AuthStorage {

  static saveTempAuth(data: UserSignInPetition) {
    this.saveItem(TEMP_AUTH_KEY, {
      petitionId: data.petitionId,
      relayId: data.relayId,
      tempToken: data.tempToken,
    });
  }

  static getTempAuth(): TempAuthData | undefined {
    return this.getItem<TempAuthData>(TEMP_AUTH_KEY);
  }

  static clearTempAuth() {
    localStorage.removeItem(TEMP_AUTH_KEY);
  }

  static saveUserAuth(data: AuthDataResponse) {
    this.saveItem(USER_AUTH_KEY, { ...data });
  }

  static getUserAuth(): AuthDataResponse | undefined {
    return this.getItem<AuthDataResponse>(USER_AUTH_KEY);
  }

  static clearUserAuth() {
    localStorage.removeItem(USER_AUTH_KEY);
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
