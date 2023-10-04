import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConfirmationPrompt } from '@base/shared/confirmation/models';


@Component({
  selector: 'tsw-confirm-dialog',
  template: `
    <mat-dialog-content>
      <div class="text-8xl px-2 py-6 flex justify-center items-center">
        <mat-icon inline color="warn">error_outline</mat-icon>
      </div>

      <div class="px-2">
        <h1 mat-dialog-title class="text-center">{{ data.prompt }}</h1>

        <div *ngIf="data.message" class="mt-3">
          <p>{{ data.message }}</p>
        </div>
      </div>


      <div class="pt-6 px-2 flex justify-center items-center">
        <div
          *ngFor="let btn of data.buttons; last as last"
          class="mx-1 flex-1"
        >
          <button
            mat-button
            class="w-full"
            [matDialogClose]="btn"
            [color]="btn.color"
            [aria-label]="btn.text"
            cdkFocusInitial
          >
            <mat-icon *ngIf="btn.icon">{{ btn.icon }}</mat-icon>
            <span>{{ btn.text }}</span>
          </button>
        </div>

      </div>
    </mat-dialog-content>
  `,
})
export class ConfirmDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: ConfirmationPrompt) {
  }
}
