import { Component, Input } from '@angular/core';

@Component({
  selector: 'tsw-logo',
  styleUrls: ['./logo.component.scss'],
  template: `
    <a
      [href]="targetLink"
      target="_blank"
      [matTooltip]="text"
      [matTooltipDisabled]="tooltipDisabled"
      [matTooltipShowDelay]="tooltipShowDelay"
    >
      <img [src]="imgSrc" [alt]="imgAlt || text">
    </a>
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
