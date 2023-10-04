import { coerceBooleanProperty } from '@angular/cdk/coercion';
import { ChangeDetectorRef, Directive, HostBinding, Input, OnInit } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { BtnStatus, ComponentStatus } from '@libs/commons';


export const DEFAULT_STYLE = `:host {
    display: inline-block;
  }`;

export const DEFAULT_STATE: BtnStatus = 'IDLE';

export type BtnDisplayText = { text: string, params: Record<string, any> | undefined };

@Directive()
export abstract class TswButtonBase implements OnInit {

  /**
   * Default button text
   */
  @Input() text = '';
  @Input() textParams: Record<string, any> | undefined;

  @Input() type: 'button' | 'submit' = 'button';

  @Input() color: ThemePalette | undefined;

  @Input() startIcon: string | undefined;
  @Input() endIcon: string | undefined;

  /**
   * Text shown in case of error state.
   */
  @Input() errorText = '';
  @Input() errorTextParams: Record<string, any> | undefined;

  /**
   * Icon shown in case of error state
   */
  @Input() errorIcon = 'error';
  @Input() errorIconColor: ThemePalette | undefined = 'warn';

  /**
   * Text shown on success state
   */
  @Input() successText = '';
  @Input() successTextParams: Record<string, any> | undefined;

  /**
   * Icon shown in case of a success state
   */
  @Input() successIcon = 'task_alt';
  @Input() successIconColor: ThemePalette | undefined = 'primary';

  /**
   * Text shown in case of loading state.
   */
  @Input() loadingText = '';
  @Input() loadingTextParams: Record<string, any> | undefined;

  @Input() loadingColor: ThemePalette | undefined;

  /**
   * Enables automatic state reset after n milliseconds.
   * Default state is pending.
   */
  @Input() autoStateRestAfter: number | undefined;

  @Input() status: ComponentStatus<BtnStatus> = this.createBtnStatus();

  displayTextAndParams: BtnDisplayText = { text: '', params: undefined };
  private autoResetTimerRef: any;

  constructor(
    private changeDetection: ChangeDetectorRef,
  ) {
  }

  private _disabled = false;

  get disabled(): boolean {
    return this._disabled;
  }

  @Input()
  set disabled(value: any) {
    const next = coerceBooleanProperty(value);
    if (next !== this._disabled) this._disabled = next;
  }

  @HostBinding('style.pointer-events')
  get pointerEv() {
    if (this.disabled) {
      return 'none';
    }
    return 'auto';
  }

  @Input()
  set state(state: BtnStatus | undefined) {
    this.status.status = state != null ? state : DEFAULT_STATE;
    this.stateChange();
  }

  ngOnInit(): void {
    this.updateDisplayText();
  }

  protected createBtnStatus() {
    return new ComponentStatus<BtnStatus>(DEFAULT_STATE);
  }

  private stateChange() {
    this.disabled = this.status.is('ERROR', 'LOAD', 'PROCESS');
    this.registerAutoStateReset();
    this.updateDisplayText();
  }

  private registerAutoStateReset() {
    if (this.autoStateRestAfter == null || this.status.is('IDLE')) {
      this.clearAutoResetTimerRef();
      return;
    }

    this.clearAutoResetTimerRef();

    this.autoResetTimerRef = setTimeout(() => {
      this.state = DEFAULT_STATE;
      this.clearAutoResetTimerRef();
      this.detectChanges();
    }, this.autoStateRestAfter);
  }

  private clearAutoResetTimerRef() {
    if (this.autoResetTimerRef) {
      clearTimeout(this.autoResetTimerRef);
      this.autoResetTimerRef = undefined;
    }
  }

  private updateDisplayText() {
    let next: BtnDisplayText;
    const text = `${ this.text }`;
    const params = this.textParams;

    const currentStatus = this.status.status;
    switch (currentStatus) {
      case 'ERROR':
        next = { text: this.errorText || text, params: this.errorTextParams || params };
        break;
      case 'SUCCESS':
        next = { text: this.successText || text, params: this.successTextParams || params };
        break;
      case 'LOAD':
      case 'PROCESS':
        next = { text: this.loadingText || text, params: this.loadingTextParams || params };
        break;
      default:
        next = { text, params };
    }

    this.displayTextAndParams = next;
    this.detectChanges();
    if (this.displayTextAndParams.text !== next.text
      && this.displayTextAndParams.params !== next.params) {
    }
  }

  private detectChanges() {
    this.changeDetection.detectChanges();
  }
}
