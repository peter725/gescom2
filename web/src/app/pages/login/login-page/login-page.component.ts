import { Component, OnInit } from '@angular/core';
import {  appName, government, ministry, prtr, ue, version } from '@base/config/app';
import { AuthManagerService, AuthProcessState } from '@base/shared/security';
import { ComponentStatus } from '@libs/commons';


@Component({
  selector: 'tsw-login-page',
  templateUrl: './login-page.component.html',
  styles: [`
    .footer {
      background-color: var(--footer-bg);
      color: var(--footer-color);
    }
  `],
})
export class LoginPageComponent implements OnInit {

  readonly currentYear = new Date().getFullYear();
  readonly organizations = [government, ministry];
  readonly organizations2 = [prtr, ue];
  readonly version = version.full;
  readonly appName = appName;
  readonly ministry = ministry;

  readonly status: ComponentStatus<AuthProcessState>;

  constructor(private authManager: AuthManagerService) {
    this.status = authManager.processStatus;
  }

  ngOnInit(): void {
    this.authManager.initProcess();

  }

}
