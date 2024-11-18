import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-section-label, section[tsw-section-label], div[tsw-section-label]',
  styles: [`:host {
    display: inline-block;
  }`],
  template: `
    <div [ngClass]="[marginClass]">
      <span class="uppercase font-Lato text-gray-500 font-bold">
        {{ label | translate: labelParams }}
      </span>
    </div>
    <mat-divider class="!border-t-black !mb-2"></mat-divider>
  `
})
export class SectionLabelComponent {
  @Input() label = '';
  @Input() labelParams: Record<string, any> | undefined;

  @Input() marginClass = 'mt-1 mb-4';
}
