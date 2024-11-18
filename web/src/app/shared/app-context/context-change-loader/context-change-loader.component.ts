import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ContextChangeConfig } from '../models';


@Component({
    selector: 'tsw-context-change-loader',
    template: `
    <div mat-dialog-content class="md:w-[35vw]  xl:w-[20vw]">
      <div class="flex flex-col justify-center items-center">
        <mat-spinner class="my-6" mode="indeterminate"></mat-spinner>

        <div class="text-center">
          <p class="font-light text-xl">{{ data.messageText | translate: data.messageParams }}</p>
          <p class="font-light">{{ 'text.other.pleaseWait' | translate }}</p>
        </div>
      </div>
    </div>
  `
})
export class ContextChangeLoaderComponent {
    constructor(@Inject(MAT_DIALOG_DATA) public readonly data: ContextChangeConfig) {
    }
}
