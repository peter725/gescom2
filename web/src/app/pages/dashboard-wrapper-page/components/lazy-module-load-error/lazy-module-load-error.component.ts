import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-lazy-module-load-error',
  template: `
    <div class="h-full w-full flex flex-col justify-center items-center">
      <div class="w-11/12 xl:w-8/12 2xl:w-6/12 py-5">
        <h1 class="text-3xl italic py-5">Ha ocurrido un error en la carga de la página</h1>

        <mat-card *ngIf="errorDetails">
          <mat-card-header>
            <mat-card-title>Detalles del error</mat-card-title>
          </mat-card-header>
          <mat-card-content><code>{{ errorDetails }}</code></mat-card-content>
        </mat-card>
      </div>

      <div class="text-center mt-5">
        <button mat-stroked-button (click)="reload()">
          <mat-icon>refresh</mat-icon>
          Recargar
        </button>
      </div>
    </div>
  `,
  styles: [],
})
export class LazyModuleLoadErrorComponent {
  @Input() errorDetails: string | undefined;

  reload() {
    window.location.href = '/';
  }
}
