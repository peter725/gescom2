import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { AuthSubject } from '../auth-subject';
import { AuthContextService } from '../services';
import { AuthViewHandler } from './auth-view-handler';


export type AuthCheckMode = 'authenticated' | 'unauthenticated';

@Directive({ selector: '[tswIsAuthenticated]' })
export class IsAuthenticatedDirective extends AuthViewHandler {

  private mode: AuthCheckMode = 'authenticated';

  constructor(
    context: AuthContextService,
    templateRef: TemplateRef<never>,
    viewContainer: ViewContainerRef
  ) {
    super(context, templateRef, viewContainer);
  }

  @Input()
  set tswIsAuthenticatedCheckMode(mode: AuthCheckMode) {
    this.mode = mode;
  }

  @Input()
  set tswIsAuthenticatedNotGranted(alternativeView: TemplateRef<never>) {
    this.alternativeView = alternativeView;
  }

  updateView(subject?: AuthSubject<unknown>) {
    subject && !this.objectInView
      ? this.showContent()
      : this.clearContainer();
  }

  protected onContextChange(subject: AuthSubject<unknown>): void {
    this.updateView(subject);
  }

}
