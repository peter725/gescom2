import { SelectionModel } from '@angular/cdk/collections';
import { Component, Input, OnDestroy } from '@angular/core';
import { FormControl } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { BehaviorSubject, debounceTime, distinctUntilChanged, ReplaySubject, takeUntil } from 'rxjs';
import { DatasetGroupContainerUI, datasetTrackByFn } from '../../sample-dataset';
import { SampleDatasetManagerService } from '../../sample-dataset-manager.service';


@Component({
  selector: 'tsw-sample-dataset-collection',
  templateUrl: './sample-dataset-collection.component.html',
})
export class SampleDatasetCollectionComponent implements OnDestroy {

  readonly displayContainers$ = new BehaviorSubject<DatasetGroupContainerUI[]>([]);
  readonly searchCtrl = new FormControl<string>('', { nonNullable: true });
  readonly selection = new SelectionModel<string>(true);
  readonly page = {
    index: 0,
    length: 100,
    size: 10,
    options: [5, 10, 25, 100]
  };

  expansionStep = -1;

  private _containers!: DatasetGroupContainerUI[];
  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  readonly trackByFn = datasetTrackByFn;

  constructor(private service: SampleDatasetManagerService) {
    // Habilitar en el futuro
    // this.monitorSearchCtrl();
  }

  @Input() set containers(containers: DatasetGroupContainerUI[]) {
    this._containers = containers;
    setTimeout(() => this.page.length = this._containers.length, 0);
    this.updateDisplayedContainers();
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  expandStep(index: number) {
    if (index >= 0 && index !== this.expansionStep) {
      this.expansionStep = index;
    }
  }

  changePage(ev: PageEvent) {
    this.page.index = ev.pageIndex;
    this.page.size = ev.pageSize;
    this.expandStep(-1);
    this.updateDisplayedContainers();
  }

  async createGroup() {
    await this.service.addResultDataset();
  }

  async removeGroup(position: string) {
    await this.service.removeResultDataset(position);
    this.expandStep(-1);
    this.selection.clear();
  }

  async removeGroups() {
    await this.service.removeResultDataset(this.selection.selected);
    this.expandStep(-1);
    this.selection.clear();
  }

  selectAllVisible() {
    const positions = this.displayContainers$.value.map(v => v.position);
    this.selection.clear(false);
    this.selection.setSelection(...positions);
  }

  private monitorSearchCtrl() {
    this.searchCtrl.valueChanges.pipe(
      takeUntil(this.destroyed$),
      debounceTime(250),
      distinctUntilChanged(),
    ).subscribe(() => this.updateDisplayedContainers());
  }

  private updateDisplayedContainers() {
    const { index, size } = this.page;
    const startIdx = index * size;
    const endIdx = startIdx + size;

    const search = this.searchCtrl.value.trim().toLowerCase();
    const list = this._containers
      .filter(group => JSON.stringify(group.formGroup.value).toLowerCase().includes(search))
      .slice(startIdx, endIdx);
    this.displayContainers$.next(list);
  }
}
