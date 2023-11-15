import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ContentChildren,
  ElementRef,
  EventEmitter,
  Input,
  Output,
  QueryList,
  TemplateRef,
  ViewChild
} from '@angular/core';
import { MatCardContent } from '@angular/material/card';
import { CustomFilterDirective } from '../../directives';
import { FilterComponent } from '../filter.component';


@Component({
  selector: 'tsw-filters-container',
  templateUrl: './filters-container.component.html',
  styleUrls: ['./filters-container.component.scss'],
})
export class FiltersContainerComponent<T> implements AfterViewInit {

  @ContentChildren(CustomFilterDirective) customFilters: QueryList<CustomFilterDirective> | undefined;
  @ViewChild(CustomFilterDirective) private simpleSearch: CustomFilterDirective | undefined;
  @ViewChild(MatCardContent, { read: ElementRef }) private cardContent: ElementRef | undefined;

  /**
   * Emits the query generated by SimpleSearchComponent.
   */
  @Output() filter = new EventEmitter<T>();
  @Input() showHiddenInput :boolean=true;

  simpleFilterTemplate: TemplateRef<FilterComponent> | undefined;
  advancedFilterTemplate: TemplateRef<FilterComponent> | undefined;

  componentIsReady = false;
  hasAdvancedFilters = false;
  showAdvancedFilters = false;
  accordionOpened = false;
  baseHeight = '';

  constructor(
    private detectionRef: ChangeDetectorRef
  ) {
  }

  ngAfterViewInit(): void {
    this.updateViewContents();
    this.setupBaseHeight();
  }

  set containerHeight(height: string) {
    if (!this.cardContent?.nativeElement) return;
    this.cardContent.nativeElement.style.maxHeight = height;
  }

  toggleAdvancedFilters() {
    const openAdvanced = !this.showAdvancedFilters;
    if (openAdvanced) {
      this.containerHeight = '';
      this.showAdvancedFilters = openAdvanced;
      setTimeout(() => this.accordionOpened = openAdvanced, 50);
    } else {
      this.containerHeight = this.baseHeight;
      this.accordionOpened = openAdvanced;
      setTimeout(() => this.showAdvancedFilters = openAdvanced, 510);
    }
  }

  private updateViewContents() {
    // Obtain the first match for simple and advanced filter components
    const list = (this.customFilters?.toArray() || []);
    const simple = list.find(elm => elm.type === 'simple');
    this.simpleFilterTemplate = simple?.templateRef || this.simpleSearch?.templateRef;

    const advanced = list.find(elm => elm.type === 'advanced');
    if (advanced) {
      this.advancedFilterTemplate = advanced.templateRef;
      this.hasAdvancedFilters = true;
    }

    this.setAsReady();
  }

  private setAsReady() {
    this.componentIsReady = true;
    this.detectionRef.detectChanges();
  }

  private setupBaseHeight() {
    if (!this.cardContent) return;

    const first = this.cardContent.nativeElement.firstChild;
    const necessaryHeight = (first?.offsetHeight || 0);
    const offset = 30;
    const baseHeight = necessaryHeight + offset + 'px';

    this.baseHeight = baseHeight;
    this.containerHeight = baseHeight;
  }
}
