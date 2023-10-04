import { NgModule, Provider } from '@angular/core';
import {
  MAT_PAGINATOR_DEFAULT_OPTIONS,
  MatPaginatorDefaultOptions,
  MatPaginatorIntl
} from '@angular/material/paginator';
import { TranslateService } from '@ngx-translate/core';

export const PAGINATOR_OPTS: MatPaginatorDefaultOptions = {
  pageSize: 25,
  pageSizeOptions: [10, 25, 50, 100],
  showFirstLastButtons: true,
  formFieldAppearance: 'standard',
};

export const PaginatorIntlProviderFactory = (translate: TranslateService): MatPaginatorIntl => {
  const paginator = new MatPaginatorIntl();
  paginator.itemsPerPageLabel = 'Elm. por página' // translate.instant('texts.other.pagination.nextPageLabel');
  paginator.nextPageLabel = 'Siguiente página'; // translate.instant('texts.other.pagination.nextPageLabel');
  paginator.previousPageLabel = 'Página anterior'; // translate.instant('texts.other.pagination.previousPageLabel');
  paginator.firstPageLabel = 'Primera página'; // translate.instant('texts.other.pagination.firstPageLabel');
  paginator.lastPageLabel = 'Última página'; // translate.instant('texts.other.pagination.lastPageLabel');
  return paginator;
};

export const PAGINATOR_OPTS_PROVIDER: Provider = {
  provide: MAT_PAGINATOR_DEFAULT_OPTIONS,
  useValue: PAGINATOR_OPTS,
};

export const PAGINATOR_INTL_PROVIDER: Provider = {
  provide: MatPaginatorIntl,
  useFactory: PaginatorIntlProviderFactory, // Create a class that extends MatPaginatorIntl,
  deps: [TranslateService],
};

@NgModule({
  providers: [
    PAGINATOR_OPTS_PROVIDER,
    PAGINATOR_INTL_PROVIDER,
  ]
})
export class PaginatorConfig {
}
