import { ChangeDetectionStrategy, Component } from '@angular/core';
import { DEFAULT_STYLE, TswButtonBase } from '../button-base';


@Component({
  selector: 'tsw-stroked-button',
  templateUrl: './stroked-button.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [DEFAULT_STYLE],
})
export class StrokedButtonComponent extends TswButtonBase {
}
