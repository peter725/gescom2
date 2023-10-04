import { Component, EventEmitter, HostBinding, Inject, Input, OnInit, Optional, Output } from '@angular/core';
import { AbstractControl, ControlContainer } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { ComponentStatus } from '@libs/commons';
import { FORM_STATUS } from '../tokens';


@Component({
  selector: 'tsw-form-actions',
  templateUrl: './form-actions.component.html',
})
export class FormActionsComponent implements OnInit {

  @Input() saveBtnLabel = 'generic.actions.save';
  @Input() saveBtnLabelParams: Record<string, any> | undefined;

  @Input() saveBtnType: 'submit' | 'button' = 'submit';
  @Input() saveBtnColor: ThemePalette = 'primary';
  @Input() saveBtnSkipConfirmation = false;

  @Input() resetBtnLabel = 'generic.actions.reset';
  @Input() resetBtnLabelParams: Record<string, any> | undefined;

  @Input() cancelBtnLabel = 'generic.actions.cancel';
  @Input() cancelBtnLabelParams: Record<string, any> | undefined;

  @Input() redirectOnCancel = true;
  @Input() cancelRedirectPath: string | string[] = ['../'];

  @Output() save = new EventEmitter<void>();
  @Output() reset = new EventEmitter<void>();
  @Output() cancel = new EventEmitter<void>();

  @HostBinding('class')
  hostClass = 'flex justify-between p-3 mt-5 border-t rounded';

  private _control: AbstractControl | undefined;

  constructor(
    @Optional() private controlContainer: ControlContainer,
    @Optional() @Inject(FORM_STATUS) public status: ComponentStatus,
  ) {
  }

  @Input('class')
  set setHostClass(hostClass: string) {
    const tmp = hostClass?.trim();
    if (tmp) this.hostClass = tmp;
  }

  @Input()
  set control(control: AbstractControl | undefined) {
    if (control) {
      this._control = control;
      this.updateActiveCtrl();
    }
  }

  get control() {
    return this._control;
  }

  ngOnInit(): void {
    this.updateActiveCtrl();
  }

  private updateActiveCtrl() {
    const control = this.controlContainer?.control;
    if (!this.control && control) {
      this._control = control;
    }
  }

}
