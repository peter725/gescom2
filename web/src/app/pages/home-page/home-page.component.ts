import { Component } from '@angular/core';
import { isDevEnvironment } from '@base/config/app';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-phone-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent {

  cards = [  ];


  lastNotification = 1;
  enablePlayground = isDevEnvironment;





}
