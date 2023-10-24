import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Notification, NotificationDef } from '@base/shared/notification/models';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-notification-dialog',
  templateUrl: './notification-dialog.component.html',
})
export class NotificationDialogComponent implements OnInit, OnDestroy {

  src: NotificationDef[] = [];
  srcIndex = 0;
  activeNotification: NotificationDef | undefined;

  autoCloseEnabled = false;
  interval: any;

  updateTimeout = 0;
  updateInterval = 0;
  elapsed = 0;
  progress = 0;

  expand = false;

  constructor(
    private ref: MatDialogRef<NotificationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Notification,
  ) {
  }

  ngOnInit(): void {
    this.setupAutoClose();
    this.data.notifications$.subscribe(src => {
      this.src = src;
      this.activeNotification = this.src[this.srcIndex];
    });
  }

  ngOnDestroy(): void {
    this.clearUpdateProgressInterval();
    this.data.clear();
  }

  toggleNotificationExpansion() {
    if (this.activeNotification) this.activeNotification.expanded = !this.activeNotification.expanded;
  }

  nextNotification() {
    this.srcIndex++;
    this.showNotification();
  }

  prevNotification() {
    this.srcIndex--;
    this.showNotification();
  }

  private showNotification() {
    this.activeNotification = this.src[this.srcIndex];
  }

  private setupAutoClose() {
    const timeout = this.data.options.ttl;
    if (timeout <= 0) {
      return;
    }

    this.updateTimeout = timeout * 1000; // ms
    this.updateInterval = this.updateTimeout / 100;
    this.elapsed = 0;

    this.autoCloseEnabled = true;
    this.updateProgress();
    this.interval = setInterval(() => this.updateProgress(), this.updateInterval);
  }

  private updateProgress() {
    this.elapsed += this.updateInterval;
    this.progress = 100 - ((this.elapsed * 100) / this.updateTimeout);
    if (this.progress <= 0) {
      this.clearUpdateProgressInterval();
      this.ref.close(undefined);
    }
  }

  private clearUpdateProgressInterval() {
    if (this.interval) {
      clearInterval(this.interval);
      this.interval = undefined;
    }
  }

}
