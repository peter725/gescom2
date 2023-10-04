import { Directive, ElementRef, Input, TemplateRef } from '@angular/core';
import { CustomFilterType } from '../constants';
import { FilterComponent } from '@base/shared/filter';


@Directive({ selector: '[tswCustomFilter]' })
export class CustomFilterDirective {

  private filterType: CustomFilterType = 'advanced';

  constructor(
    public templateRef: TemplateRef<FilterComponent>,
    private elementRef: ElementRef<FilterComponent>,
  ) {
    templateRef.createEmbeddedView(elementRef.nativeElement);
  }

  @Input('tswCustomFilter')
  set tswCustomFilter(type: CustomFilterType) {
    this.filterType = type;
  }

  get type(): CustomFilterType {
    return this.filterType;
  }

}
