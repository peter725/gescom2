import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-logo',
  styleUrls: ['./logo.component.scss'],
  template: `
  <a *ngIf="targetLink; else noLinkLogo"
    class="linked"
    [href]="targetLink"
    target="_blank"
    [matTooltip]="text"
    [matTooltipDisabled]="tooltipDisabled"
    [matTooltipShowDelay]="tooltipShowDelay"
  >
    <img [src]="imgSrc" [alt]="imgAlt || text">
  </a>
  <ng-template #noLinkLogo>
    <a
      [matTooltip]="text"
      [matTooltipDisabled]="tooltipDisabled"
      [matTooltipShowDelay]="tooltipShowDelay"
    >
      <img [src]="imgSrc" [alt]="imgAlt || text">
    </a>
  </ng-template>
  `,
})
export class LogoComponent {
  @Input() text = '';

  @Input() tooltipDisabled = false;
  @Input() tooltipShowDelay = 1000;

  @Input() targetLink = '';

  @Input() imgSrc = '';
  @Input() imgAlt = '';
}
