import { ChangeDetectionStrategy, Component } from '@angular/core';
import { DEFAULT_STYLE, TswButtonBase } from '../button-base';


@Component({
  selector: 'tsw-raised-button',
  templateUrl: './raised-button.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [DEFAULT_STYLE],
})
export class RaisedButtonComponent extends TswButtonBase {
}
