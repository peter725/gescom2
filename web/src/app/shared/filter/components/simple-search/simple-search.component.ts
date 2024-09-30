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

  @Input() showToggleHidden: boolean = true;

  readonly resourceName = 'global';
  override readonly searchMode = SearchMode.SEARCH;

  protected buildQueryForm() {
    return this.fb.group<ControlsOf<SimpleSearchForm>>({
      search: this.fb.control(null),
      state: this.fb.control(null),
    });
  }

}
