import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Notification, NotificationDef, NotificationOptions, NotificationSrc } from './models';
import { NotificationDialogComponent } from './notification-dialog.component';
import { Subject } from 'rxjs';


@Injectable({ providedIn: 'root' })
export class NotificationService {


  private notificationCloseSubject = new Subject<void>();
  private activeRef: MatDialogRef<NotificationDialogComponent> | undefined;
  private activeData: Notification | undefined;

  constructor(
    private dialog: MatDialog,
    private translate: TranslateService,
  ) {
  }

  closeNotification() {
    this.notificationCloseSubject.next();
  }

  afterClosed() {
    return this.notificationCloseSubject.asObservable();
  }

  show(src: NotificationSrc, opts: Partial<NotificationOptions> = {}) {
    if (this.activeRef) {
      this.activeData?.push(this.buildNotificationDef(src));
      return;
    }

    const data = new Notification(this.buildNotificationDef(src), this.buildNotificationOpts(opts));
    this.activeData = data;

    this.activeRef = this.dialog.open(NotificationDialogComponent, this.buildDialogConfig(data));
    this.activeRef.afterClosed().subscribe(() => {
      this.activeData = undefined;
      this.activeRef = undefined;
    });
  }

  private buildNotificationDef(src: NotificationSrc): NotificationDef {
    return {
      title: this.getNotificationTitle(src),
      message: this.getNotificationMessage(src),
      icon: this.getNotificationIcon(src),
      color: this.getNotificationColor(src),
      type: src.type,
      details: src.details || [],
      expanded: false,
    };
  }

  private getNotificationTitle(src: NotificationSrc) {
    if (src.title) {
      return this.translate.instant(src.title, src.titleParams);
    }

    let title;
    switch (src.type) {
      case 'warn':
        title = 'text.other.warning';
        break;
      case 'danger':
        title = 'text.other.error';
        break;
      default:
        title = 'text.other.info';
    }

    return this.translate.instant(title, src.titleParams);
  }

  private getNotificationIcon(src: NotificationSrc) {
    if (src.icon) {
      return src.icon;
    }

    switch (src.type) {
      case 'warn':
        return 'priority_high';
      case 'danger':
        return 'report';
      default:
        return 'info';
    }
  }

  private getNotificationMessage(src: NotificationSrc) {
    return this.translate.instant(src.message, src.messageParams);
  }

  private getNotificationColor(src: NotificationSrc) {
    if (src.color) {
      return src.color;
    }

    switch (src.type) {
      case 'danger':
        return 'warn';
      case 'warn':
      case 'info':
        return 'accent';
      default:
        return undefined;
    }
  }

  private buildNotificationOpts(opts: Partial<NotificationOptions>): NotificationOptions {
    const ttl = opts?.ttl || 0;
    return { ttl };
  }

  private buildDialogConfig(data: Notification) {
    return {
      data,
      minWidth: '30%',
      maxWidth: '80%',
      minHeight: '10%',
      hasBackdrop: false,
      position: {
        top: '30vh',
        right: '30vw' },
    } as MatDialogConfig<Notification>;
  }

}
