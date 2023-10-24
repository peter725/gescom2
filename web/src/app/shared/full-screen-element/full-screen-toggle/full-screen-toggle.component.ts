import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FullScreenService } from '../full-screen.service';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-full-screen-toggle',
  template: `
    <ng-container *ngIf="(status$ | async) as status">
      <button
        *ngIf="!status.enabled"
        mat-button
        matTooltip="Pantalla completa"
        (click)="show()"
      >
        <mat-icon>fullscreen</mat-icon>
      </button>

      <button
        *ngIf="status.enabled"
        mat-button
        matTooltip="Cerrar pantalla completa"
        (click)="close()"
      >
        <mat-icon>fullscreen_exit</mat-icon>
      </button>
    </ng-container>
  `,
})
export class FullScreenToggleComponent {

  @Input() elementSelector: string | undefined;

  status$: Observable<{ enabled: boolean }>;

  constructor(private service: FullScreenService) {
    this.status$ = service.status$;
  }

  async show() {
    if (this.elementSelector) {
      this.service.show(this.elementSelector);
    }
  }

  async close() {
    if (this.elementSelector) {
      await this.service.close();
    }
  }
}
