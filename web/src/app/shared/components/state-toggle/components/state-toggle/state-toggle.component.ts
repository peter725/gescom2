import { Component, Input } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { NotificationService } from '@base/shared/notification';
import { AppError, BtnStatus, ComponentStatus } from '@libs/commons';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { ModelStates, SimpleModel, StatefulAltModel, StatefulModel } from '@libs/sdk/common';
import { Subscription } from 'rxjs';


const BTN_ON_TEXT = 'generic.actions.turnOn';
const BTN_ON_ICON = 'toggle_on';
const BTN_ON_COLOR: ThemePalette = 'primary';
const BTN_OFF_TEXT = 'generic.actions.turnOff';
const BTN_OFF_ICON = 'toggle_off';
const BTN_OFF_COLOR: ThemePalette = 'warn';

@Component({
  selector: 'tsw-state-toggle',
  template: `
    <tsw-button
      [status]="btnStatus"
      [matTooltip]="btnText | translate"
      matTooltipPosition="above"
      matTooltipShowDelay="500"
      [startIcon]="btnIcon"
      [color]="btnColor"
      [disabled]="(btnStatus.is$('PROCESS', 'LOAD') | async)"
      (click)="toggleStatus()"
    ></tsw-button>
  `,
})
export class StateToggleComponent<T extends (StatefulModel | StatefulAltModel)> {

  @Input() resource = '';

  resourceStatus: StatefulAltModel | undefined;

  btnText = '';
  btnIcon: string | undefined;
  btnColor: ThemePalette;
  readonly btnStatus = new ComponentStatus<BtnStatus>('IDLE');

  private _id: string | number | undefined;
  private _data: T | undefined;
  private activeOperation: Subscription | undefined;

  constructor(
    private crudService: CrudImplService<T, string | number>,
    private notification: NotificationService,
  ) {
  }

  @Input()
  set data(data: T) {
    this._data = data;
    // Temporal, hay que ver como extraer el ID de un modelo
    this._id = (this._data as unknown as SimpleModel).id;
    this.updateResourceState();
    this.updateBtnDetails();
  }

  /**
   * Use this value as an ID instead of the one received from data
   */
  @Input()
  set forceId(id: string | number) {
    this._id = id;
  }

  toggleStatus() {
    if (!this.resource || !this._data) return;

    this.btnStatus.status = 'PROCESS';
    if (this.activeOperation) {
      this.activeOperation.unsubscribe();
      this.activeOperation = undefined;
    }

    const id = this._id || 0;
    const payload = { status: this.getNextStatus() };
    const config: RequestConfig = {
      resourceName: this.resource,
      pathParams: { id },
    };
    this.activeOperation = this.crudService.changeState(id, payload, config).subscribe({
      next: result => {
        this._data = result;
        this.btnStatus.status = 'IDLE';
        this.updateResourceState();
        this.updateBtnDetails();
        if(payload.status === ModelStates.OFF) location.reload();
      },
      error: e => {
        this.btnStatus.status = 'ERROR';
        const err = AppError.parse(e);
        this.notification.show({
          type: 'warn',
          message: err.message,
        });
        this.updateBtnDetails();
      },
    });

  }

  private updateResourceState() {
    if (!this._data) return;

    let stateId = 0;
    if ('state' in this._data) {
      stateId = this._data.state;
    } else if ('stateId' in this._data) {
      stateId = this._data.stateId;
    }
    this.resourceStatus = {
      stateId,
      stateName: this._data.stateName + '',
    };
  }

  private updateBtnDetails() {
    if (!this.resourceStatus) return;

    // El texto mostrado indica el valor futuro que tendrá el recurso.
    if (this.resourceStatus.stateId === ModelStates.ON) {
      this.btnText = '' + BTN_OFF_TEXT;
      this.btnIcon = '' + BTN_ON_ICON;
      this.btnColor = BTN_ON_COLOR;
    } else{
      this.btnText = '' + BTN_ON_TEXT;
      this.btnIcon = '' + BTN_OFF_ICON;
      this.btnColor = BTN_OFF_COLOR;
    }
  }

  private getNextStatus() {
    if (!this.resourceStatus) return ModelStates.ON;

    return this.resourceStatus.stateId === ModelStates.ON
      ? ModelStates.OFF
      : ModelStates.ON
      ;
  }
}
