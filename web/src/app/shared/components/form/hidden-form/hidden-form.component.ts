import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { HiddenFormData } from '../models';


/**
 * Hidden basic HTML submittable form. This form should be used
 * for redirects and certain actions that cannot be done by
 * an HTTP REST mode.
 */
@Component({
  selector: 'tsw-hidden-form',
  template: `<form
    *ngIf="data"
    #hiddenForm
    class="hidden"
    [action]="data.action"
    [method]="data.method"
    [target]="target"
  >
    <input
      *ngFor="let param of data.params"
      type="hidden"
      [name]="param.key"
      [value]="param.value"
    >
  </form>`,
})
export class HiddenFormComponent implements OnInit {
  /**
   * Auto-submit form after given number of milliseconds.
   */
  @Input() autoSubmitAfter = -1;
  /**
   * Maximum number of retries.
   */
  @Input() submitMaxRetries = 3;
  /**
   * Number of milliseconds to retry submitting after.
   */
  @Input() submitRetryTimeout = 1000;
  /**
   * HTML form data.
   */
  @Input() data: HiddenFormData | undefined;
  /**
   * HTML form target.
   */
  @Input() target: '_self' | '_blank' = '_self';

  @ViewChild('hiddenForm') hiddenForm: ElementRef<HTMLFormElement> | undefined;

  private submitRetries = 0;

  ngOnInit(): void {
    this.registerAutoSubmit();
  }

  submit() {
    if (!this.hiddenForm?.nativeElement) {
      return this.retrySubmit();
    }

    this.hiddenForm.nativeElement.submit();
  }

  private registerAutoSubmit() {
    if (this.autoSubmitAfter < 0) return;

    setTimeout(() => this.submit(), this.autoSubmitAfter);
  }

  private retrySubmit() {
    if (this.submitRetries < this.submitMaxRetries) {
      this.submitRetries++;
      setTimeout(() => this.submit(), this.submitRetryTimeout);
    }
  }

}
