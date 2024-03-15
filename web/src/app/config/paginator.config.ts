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
  formFieldAppearance: 'fill' ,
};

export const spanishRangeLabel = (page: number, pageSize: number, length: number) => {
  if (length == 0 || pageSize == 0) { return `0 de ${length}`; }
  
  length = Math.max(length, 0);

  const startIndex = page * pageSize;

  // If the start index exceeds the list length, do not try and fix the end index to the end.
  const endIndex = startIndex < length ?
      Math.min(startIndex + pageSize, length) :
      startIndex + pageSize;

  return `${startIndex + 1} - ${endIndex} de ${length}`;
}

export const PaginatorIntlProviderFactory = (translate: TranslateService): MatPaginatorIntl => {
  const paginator = new MatPaginatorIntl();
  paginator.itemsPerPageLabel = translate.instant('text.other.pagination.itemsPerPageLabel');
  paginator.nextPageLabel = translate.instant('text.other.pagination.nextPageLabel');
  paginator.previousPageLabel = translate.instant('text.other.pagination.previousPageLabel');
  paginator.firstPageLabel = translate.instant('text.other.pagination.firstPageLabel');
  paginator.lastPageLabel = translate.instant('text.other.pagination.lastPageLabel');
  paginator.getRangeLabel = spanishRangeLabel;
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
