import { Directive, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { ConfirmationService } from './confirmation.service';
import { GENERIC_CONFIRMATION_PROMPT } from './constants';


type ExpectedEv = 'click' | 'submit';

@Directive({ selector: '[tswConfirmAction]' })
export class ConfirmActionDirective {

  private _expectedEvent: ExpectedEv = 'click';

  @Input() confirmPrompt = GENERIC_CONFIRMATION_PROMPT;
  @Input() confirmPromptParams: Record<string, any> | undefined;

  @Input() confirmMessage: string | undefined;
  @Input() confirmMessageParams: Record<string, any> | undefined;

  /**
   * Disable the confirmation requirement. If the confirmation is
   * disabled, the directive won't emit any event.
   */
  @Input() disableConfirmation = false;

  /**
   * Emits when confirmation prompt was resolved with any result.
   */
  @Output() resolved = new EventEmitter<any>;
  /**
   * Emits when confirmation prompt was resolved with a CANCELLED result.
   */
  @Output() cancelled = new EventEmitter<any>;
  /**
   * Emits when confirmation prompt was resolved with a CONFIRMED result.
   */
  @Output() confirmed = new EventEmitter<any>;
  /**
   * Emits when confirmation prompt was resolved with a REJECTED result.
   */
  @Output() rejected = new EventEmitter<any>;

  constructor(private confirmationService: ConfirmationService) {
  }

  @Input()
  set tswConfirmAction(value: ExpectedEv | string | undefined | null) {
    const isValid = (value != null && ['click', 'submit', 'both'].includes(value));
    this.expectedEvent = isValid ? (value as ExpectedEv) : 'click';
  }

  @HostListener('click', ['$event'])
  confirmClick() {
    if (this.expectedEvent === 'click') this.confirmAction();

  }

  @HostListener('submit', ['$event'])
  confirmSubmit() {
    if (this.expectedEvent === 'submit') this.confirmAction();
  }

  get expectedEvent() {
    return this._expectedEvent;
  }

  set expectedEvent(value: ExpectedEv) {
    if (value !== this._expectedEvent) this._expectedEvent = value;
  }

  private confirmAction() {
    if (this.disableConfirmation) {
      this.resolved.emit(undefined);
      this.confirmed.emit(undefined);
      return;
    }

    const ref = this.confirmationService.confirm({
      prompt: this.confirmPrompt,
      promptParams: this.confirmPromptParams,
      message: this.confirmMessage,
      messageParams: this.confirmMessageParams,
    });
    ref.resolved().subscribe(result => this.resolved.emit(result));
    ref.isCancelled().subscribe(result => this.cancelled.emit(result));
    ref.isConfirmed().subscribe(result => this.confirmed.emit(result));
    ref.isRejected().subscribe(result => this.rejected.emit(result));
  }

}
