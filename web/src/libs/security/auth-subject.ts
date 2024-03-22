import {GEModule} from "@libs/sdk/module";

export abstract class AuthSubject<T> {
  protected constructor(private readonly details: T) {
  }

  getDetails(): T {
    return this.details;
  }

  hasModule(moduleCode: string): boolean {
    return this.getModules().filter((e) => e.code === moduleCode).length > 0;
  }

  hasScope(moduleCode: string, scopeCode: string | undefined): boolean {
    return this.getModules()
      .filter((e) => e.code === moduleCode)
      .flatMap((e) => e.scopes)
      .filter((e) => e === scopeCode).length > 0;

  }

  canRead(moduleCode: string) {
    return this.hasScope(moduleCode,"rr");
  }

  canWrite(moduleCode: string) {
    return this.hasScope(moduleCode,"ww");
  }

  canDelete(moduleCode: string) {
    return this.hasScope(moduleCode,"dd");
  }

  abstract isAuthenticated(): boolean;

  abstract getModules(): GEModule[];

  abstract getProfile(): string;

  abstract equals(value: unknown): boolean;

}

export class UnauthenticatedSubject extends AuthSubject<Record<string, unknown>> {
  constructor() {
    super({});
  }

  isAuthenticated() {
    return false;
  }

  getModules(): GEModule[] {
    return [];
  }

  getProfile(): string {
    return "";
  }

  getSurnames(): string {
    return "";
  }

  equals(): boolean {
    return false;
  }
}
