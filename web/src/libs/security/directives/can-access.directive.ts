import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';
import { ResourceAccessKey } from '../models';
import {  AuthContextService } from '../services';
import { AuthViewHandler } from './auth-view-handler';


@Directive({ selector: '[tswCanAccess]' })
export class CanAccessDirective extends AuthViewHandler {
  private resource: ResourceAccessKey | undefined;

  constructor(
    context: AuthContextService,
    templateRef: TemplateRef<never>,
    viewContainer: ViewContainerRef
  ) {
    super(context, templateRef, viewContainer);
  }

  @Input()
  set tswCanAccess(resource: ResourceAccessKey) {
    this.resource = resource;
  }

  @Input()
  set tswCanAccessAlternativeView(alternativeView: TemplateRef<never>) {
    this.alternativeView = alternativeView;
  }

  async updateView() {
    try {
      if (!this.resource) {
        this.showContent();
        return;
      }
      const granted = this.context.instant().hasModule(this.resource);
      if (granted) this.showContent();
      else this.clearContainer();
    } catch (e) {
      this.clearContainer();
    }
  }

  protected onContextChange(): void {
    this.updateView();
  }

}
