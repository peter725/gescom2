import { Component } from '@angular/core';
import { dgc, appName, version } from '@base/config/app';


@Component({
  selector: 'tsw-not-found-page',
  templateUrl: './not-found-page.component.html',
  styleUrls: ['./not-found-page.component.scss']
})
export class NotFoundPageComponent {
  appName = appName;
  version = version.full;
  dgc = dgc;
}
