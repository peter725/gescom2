import { Component } from '@angular/core';

@Component({
  selector: 'tsw-lazy-module-loading',
  template: `
    <div class="w-full h-full flex justify-center items-center">
      <mat-progress-spinner
        color="primary"
        mode="indeterminate"
      ></mat-progress-spinner>
    </div>
  `,
  styles: [],
})
export class LazyModuleLoadingComponent {
}
