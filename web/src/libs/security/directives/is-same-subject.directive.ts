import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { AuthSubject } from '../auth-subject';
import { AuthContextService } from '../services';
import { AuthViewHandler } from './auth-view-handler';


type CheckMode = 'eq' | 'ne';

@Directive({ selector: '[tswIsSameSubject]' })
export class IsSameSubjectDirective extends AuthViewHandler {
  private checkSubject: unknown;
  private mode: CheckMode = 'eq';

  constructor(
    context: AuthContextService,
    templateRef: TemplateRef<never>,
    viewContainer: ViewContainerRef
  ) {
    super(context, templateRef, viewContainer);
  }

  @Input()
  set tswIsSameSubject(subject: unknown) {
    this.checkSubject = subject;
  }

  @Input()
  set tswIsSameSubjectMode(mode: CheckMode) {
    this.mode = mode;
  }

  @Input()
  set tswIsSameSubjectNoMatch(alternativeView: TemplateRef<never>) {
    this.alternativeView = alternativeView;
  }

  updateView(subject?: AuthSubject<unknown>): void {
    this.mode === 'eq'
      ? this.checkEqualSubject(subject)
      : this.checkDistinctSubject(subject);
  }

  protected onContextChange(subject: AuthSubject<unknown>): void {
    this.updateView(subject);
  }

  private checkEqualSubject(subject?: AuthSubject<unknown>) {
    if (subject?.equals(this.checkSubject) && !this.objectInView) {
      this.showContent();
    } else {
      this.clearContainer();
    }
  }

  private checkDistinctSubject(subject?: AuthSubject<unknown>) {
    if (subject?.equals(this.checkSubject) && !this.objectInView) {
      this.clearContainer();
    } else {
      this.showContent();
    }
  }

}
