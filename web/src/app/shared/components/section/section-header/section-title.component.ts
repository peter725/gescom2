import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-section-title, section[tsw-section-title], div[tsw-section-title]',
  styles: [`:host {
    display: block
  }`],
  template: `
    <div
      class="flex justify-between items-center"
      [ngClass]="[bgClass, borderClass, paddingClass, marginClass]"
    >
      <div class="flex items-center">
        <mat-icon *ngIf="icon" class="mr-2">{{ icon }}</mat-icon>
        <h1 [class]="labelClass">{{ label | translate: labelParams }}</h1>
      </div>

      <ng-content></ng-content>
    </div>
  `
})
export class SectionTitleComponent {

  @Input() label = '';
  @Input() labelParams: Record<string, any> | undefined;
  @Input() labelClass = 'uppercase';

  @Input() icon: string | undefined;

  @Input() bgClass = '';
  @Input() marginClass = 'mb-4';
  @Input() paddingClass = 'py-3 px-2';
  @Input() borderClass = 'border-b border-b-slate-200';

}
