import { Directive, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SearchMode } from '@base/shared/filter';
import { AppQuerySource, ControlsOf } from '@libs/commons';
import { firstValueFrom, ReplaySubject } from 'rxjs';
import { FilterService } from '../filter.service';


@Directive()
export abstract class FilterComponent<T extends AppQuerySource = AppQuerySource> implements OnInit, OnDestroy {

  @Output() filter = new EventEmitter<void>();

  abstract readonly resourceName: string;
  readonly searchMode: SearchMode = SearchMode.FILTER;
  readonly form: FormGroup<ControlsOf<T>>;

  protected destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    protected filterService: FilterService,
    protected fb: FormBuilder,
  ) {
    this.form = this.buildQueryForm();
  }

  async ngOnInit() {
    await this.loadData();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  search() {
    this.filterService.set({
      name: this.resourceName,
      mode: this.searchMode,
      source: this.getQuerySource(),
      useParams: { handling: '' }
    });
  }

  clear() {
    this.form.reset();
    this.filterService.clear();
  }

  protected getQuerySource(): T {
    const obj: any = this.form.getRawValue();
    return { ...obj };
  }

  protected updateSource(value: T | AppQuerySource) {
    const obj = this.form.getRawValue();
    const sourceKeys = Object.keys(obj);
    const sourceUpdate = { ...obj } as AppQuerySource;
    Object.entries(value).forEach(([key, value]) => {
      if (sourceKeys.includes(key)) {
        sourceUpdate[key] = value || undefined;
      }
    });
    const update = { ...sourceUpdate } as any;
    this.form.patchValue(update, { emitEvent: false });
  }

  private async loadData() {
    console.log("loadData filter.component.ts");
    try {
      const external = await firstValueFrom(this.filterService.get(this.resourceName));
      this.updateSource(external.query.getSource());
    } catch (e) {
      this.form.reset();
    }
  }

  protected abstract buildQueryForm(): FormGroup<ControlsOf<T>>;
}
