import { Component } from '@angular/core';
import { isDevEnvironment } from '@base/config/app';

@Component({
  selector: 'tsw-phone-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent {

  cards = [  ];


  lastNotification = 1;
  enablePlayground = isDevEnvironment;





}
