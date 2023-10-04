import { Component, ElementRef, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatAutocomplete } from '@angular/material/autocomplete';
import { MatFormFieldControl } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { extractModelPath } from '@libs/utils/extract-model-path';
import { debounceTime, fromEvent, takeUntil } from 'rxjs';
import { CustomSelectBaseComponent } from '../base-select.component';
import { ComboValue } from '../combo';


@Component({
  selector: 'tsw-custom-autocomplete',
  templateUrl: './custom-autocomplete.component.html',
  providers: [
    { provide: MatFormFieldControl, useExisting: CustomAutocompleteComponent },
  ]
})
export class CustomAutocompleteComponent<T = ComboValue>
  extends CustomSelectBaseComponent<T>
  implements OnInit {

  static nextId = 0;

  @ViewChild(MatInput, { static: true }) inputElm: MatInput | undefined;
  @ViewChild(MatAutocomplete, { static: true }) autocompleteElm: MatAutocomplete | undefined;

  @Input() optionTmpl: TemplateRef<never> | undefined;
  @Input() displayWithFn: ((value: T) => string) | null = (value: T) => extractModelPath(value, this.displayPath);

  @ViewChild(MatInput, { read: ElementRef, static: true })
  set selectRef(elm: ElementRef) {
    this.elementRef = elm;
  }

  override ngOnInit() {
    super.ngOnInit();
    this.registerSearchListener();
  }

  selectValue(value: T | null) {
    this.onChange(value || undefined);
    this.value = value;
  }

  getComponentId(): string {
    return `tsw-custom-autocomplete-${ CustomAutocompleteComponent.nextId++ }`;
  }

  protected override updateShouldLabelFloat() {
    let next = false;
    if (!this.disabled) {
      next = (this.focused || !this.empty || !!this.inputElm?.value);
    }
    if (this.shouldLabelFloat !== next) this.shouldLabelFloat = next;
  }

  private registerSearchListener() {
    if (!this.elementRef) return;

    fromEvent(this.elementRef.nativeElement, 'keydown').pipe(
      takeUntil(this.destroyed$),
      debounceTime(1000),
    ).subscribe(ev => {
      if (!ev) return;
      const v = this.inputElm?.value?.trim();
      if (!v && this.value) {
        this.selectValue(null);
      }
      this.search(this.inputElm?.value?.trim());
    });
  }
}
