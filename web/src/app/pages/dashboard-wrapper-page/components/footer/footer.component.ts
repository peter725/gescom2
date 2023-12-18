import { Component } from '@angular/core';
import { dgc, appName, version } from '@base/config/app';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-footer, footer[tsw-footer]',
  template: `
    <div>&nbsp;</div>
    <div class="text-center">
      {{ appName }} ({{ version }}) &copy; {{ currentYear }}
      <a [href]="dgc.web" target="_blank" class="font-bold">{{ dgc.shortName }}</a>
    </div>
  `,
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent {
  appName = appName;
  currentYear = new Date().getFullYear();
  dgc = dgc;
  version = version.full;
}
