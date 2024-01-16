import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: any): void {
    const userString = JSON.stringify(user);
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, userString);
  }

  public getUser(): any {
    return JSON.parse(window.sessionStorage.getItem(USER_KEY) as string);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }
}
