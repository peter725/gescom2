import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { AppContextService } from '@base/shared/app-context';
import { DataQueryService } from '../../data-query.service';
import { DataQueryConfigForm } from '../../data-query.model';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-data-query-config-preparation',
  templateUrl: './data-query-config-preparation.component.html',
  styleUrls: ['./data-query-config-preparation.component.scss'],
})
export class DataQueryConfigPreparationComponent extends BaseListPageComponent implements OnInit{
  readonly form: FormGroup<DataQueryConfigForm>;
  readonly searchCtrl = new FormControl<string>('', { nonNullable: true });
  readonly resourceName = 'dataQuery';

  reutilizarConsulta = false;

  dataArray = [
    {name: 'Consulta guardada 1',},
    {name: 'Consulta guardada 2',},
    {name: 'Consulta guardada 3',},
  ];

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    private fb: FormBuilder,
    private ctx: AppContextService,
    private service: DataQueryService
  ) {
    super(crudService, filterService);
    this.form = service.form;
  }

  protected getColumns() {
    return ['select', 'name', 'actions'];
  }
}
