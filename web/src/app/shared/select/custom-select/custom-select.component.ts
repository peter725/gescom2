import { Component, ElementRef, Input, TemplateRef, ViewChild } from '@angular/core';
import { MatFormFieldControl } from '@angular/material/form-field';
import { MatSelect } from '@angular/material/select';
import { CustomSelectBaseComponent } from '@base/shared/select';
import { ComboValue } from '../combo';


@Component({
  selector: 'tsw-custom-select',
  templateUrl: './custom-select.component.html',
  providers: [
    { provide: MatFormFieldControl, useExisting: CustomSelectComponent },
  ]
})
export class CustomSelectComponent<T = ComboValue> extends CustomSelectBaseComponent<T> {

  static nextId = 0;

  @Input() triggerTmpl: TemplateRef<any> | undefined;
  @Input() optionTmpl: TemplateRef<any> | undefined;

  @ViewChild(MatSelect, { static: true }) selectElm: MatSelect | undefined;

  @ViewChild(MatSelect, { read: ElementRef, static: true })
  set selectRef(elm: ElementRef) {
    this.elementRef = elm;
  }

  override openSelectionOptions(): void {
    super.openSelectionOptions();
    this.selectElm?.open();
  }

  getComponentId(): string {
    return `tsw-custom-select-${ CustomSelectComponent.nextId++ }`;
  }

}
