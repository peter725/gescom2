import { Component, Input } from '@angular/core';
import { ControlsOf } from '@libs/commons';
import { SearchMode, SimpleSearchForm } from '../../models';
import { FilterComponent } from '../filter.component';

@Component({
  selector: 'tsw-simple-search',
  templateUrl: './simple-search.component.html',
  styleUrls: ['./simple-search.component.scss'],
})
export class SimpleSearchComponent extends FilterComponent<SimpleSearchForm> {

  readonly resourceName = 'global';
  override readonly searchMode = SearchMode.SEARCH;
  @Input() showHiddenInput: boolean = true;

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<SimpleSearchForm>>({
      search: this.fb.control(null),
      state: this.fb.control(null),
    });
  }

}
