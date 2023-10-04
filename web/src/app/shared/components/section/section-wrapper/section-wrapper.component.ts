import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-section-wrapper, section[tsw-section-wrapper], div[tsw-section-wrapper]',
  styles: [`:host {
    display: block;
  }`],
  template: `
    <tsw-section-title
      [label]="headerLabel"
      [labelParams]="headerLabelParams"
      [icon]="icon"
    >
      <ng-content select="[section-header-actions]"></ng-content>
    </tsw-section-title>

    <ng-content select="[section-content]"></ng-content>
  `,
})
export class SectionWrapperComponent {

  @Input() headerLabel!: string;
  @Input() headerLabelParams: Record<string, any> | undefined;

  @Input() icon: string | undefined;

}
