import { Component } from '@angular/core';
import { AuthManagerService, AuthProcessState } from '@base/shared/security';
import { ComponentStatus } from '@libs/commons';
import { Observable } from 'rxjs';

@Component({
  selector: 'tsw-login-status',
  templateUrl: './login-status.component.html',
  styles: [`.login__status__indicator {
    height: 7rem;
    width: 7rem;
    font-size: 7rem;
  }`],
})
export class LoginStatusComponent {

  status: ComponentStatus<AuthProcessState>;
  message$: Observable<string>;

  constructor(private authManager: AuthManagerService) {
    this.status = authManager.processStatus;
    this.message$ = authManager.processMessage$;
  }

}
