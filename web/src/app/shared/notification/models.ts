import { ThemePalette } from '@angular/material/core';
import { BehaviorSubject } from 'rxjs';


export type NotificationType = undefined | 'info' | 'warn' | 'danger';

/**
 * Data source to build a notification
 */
export interface NotificationSrc {
  message: string;

  messageParams?: Record<string, any>;

  icon?: string;

  title?: string;

  titleParams?: Record<string, any>;

  details?: string[];

  color?: ThemePalette;

  type?: NotificationType;

  ttl?: number;
}

/**
 * Notification contents
 */
export interface NotificationDef {
  icon: string;
  title: string;
  message: string;
  color: ThemePalette;
  type: NotificationType;
  details: string[];

  expanded: boolean;
}

/**
 * Data structure used to configure the notification
 */
export interface NotificationOptions {
  /**
   * Number of seconds before dismissing the notification automatically. To disable
   * set the value to 0.
   */
  ttl: number;
}

export class Notification {

  private readonly notification$ = new BehaviorSubject<NotificationDef[]>([]);

  constructor(
    private src: NotificationDef,
    private opts: NotificationOptions,
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

  push(src: NotificationDef) {
    const copy = [...this.notification$.value];
    copy.push(src);
    this.notification$.next(copy);
  }

  clear() {
    this.notification$.next([]);
    this.notification$.complete();
  }
}
