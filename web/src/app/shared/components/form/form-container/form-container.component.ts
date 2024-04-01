import { Component, Host, Inject, Optional } from '@angular/core';
import { FORM_STATUS } from '@base/shared/components/form';
import { ComponentStatus } from '@libs/commons';
import { BehaviorSubject, of, ReplaySubject, Subject } from 'rxjs';


@Component({
  selector: 'tsw-form-container',
  templateUrl: './form-container.component.html',
  styleUrls: ['./form-container.component.scss'],
})
export class FormContainerComponent {
  private noAnimation = new BehaviorSubject(false);
  constructor(
    @Optional() @Inject(FORM_STATUS) public status: ComponentStatus,
  ) {
  }

  get showLoadingAnimation() {
    if (!this.status) {
      return this.noAnimation.asObservable();
    }
    return this.status.is$('LOAD', 'PROCESS');
  }
}
