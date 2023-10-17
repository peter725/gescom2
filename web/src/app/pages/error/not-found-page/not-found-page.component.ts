import { Component } from '@angular/core';
import { aesan, appName, version } from '../../../config/app';


@Component({
  selector: 'tsw-not-found-page',
  templateUrl: './not-found-page.component.html',
  styleUrls: ['./not-found-page.component.scss']
})
export class NotFoundPageComponent {
  appName = appName;
  version = version.full;
  aesan = aesan;
}
