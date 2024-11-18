import { ThemePalette } from '@angular/material/core';
import { BehaviorSubject } from 'rxjs';


export type SecurityType = undefined | 'info' | 'warn' | 'danger';

/**
 * Data source to build a notification
 */
export interface SecuritySrc {
  message: string;

  messageParams?: Record<string, any>;

  icon?: string;

  title?: string;

  titleParams?: Record<string, any>;

  details?: string[];

  color?: ThemePalette;

  type?: SecurityType;

  ttl?: number;
}

/**
 * Notification contents
 */
export interface SecurityDef {
  icon: string;
  title: string;
  message: string;
  color: ThemePalette;
  type: SecurityType;
  details: string[];
  expanded: boolean;
}

/**
 * Data structure used to configure the notification
 */
export interface SecurityOptions {
  /**
   * Number of seconds before dismissing the notification automatically. To disable
   * set the value to 0.
   */
  ttl: number;
}

export class Security {

  response = false;

  private readonly notification$ = new BehaviorSubject<SecurityDef[]>([]);

  constructor(
    private src: SecurityDef,
    private opts: SecurityOptions,
  ) {
    this.push(src);
  }

  get notifications$() {
    return this.notification$.asObservable();
  }

  get options() {
    return this.opts;
  }

  get length() {
    return this.notification$.value.length;
  }

  push(src: SecurityDef) {
    const copy = [...this.notification$.value];
    copy.push(src);
    this.notification$.next(copy);
  }

  clear() {
    this.notification$.next([]);
    this.notification$.complete();
  }
}
