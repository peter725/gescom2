import { ThemePalette } from '@angular/material/core';
import { MatPaginatorSelectConfig } from '@angular/material/paginator';

export interface PaginatorOptions {
  length: number;
  pageSize: number;
  pageIndex: number;
  pageSizeOptions?: number[];
  showFirstLastButtons?: boolean;
  hidePageSize?: boolean;
  disabled?: boolean;
  selectConfig?: MatPaginatorSelectConfig;
  color?: ThemePalette;
}

// export type PaginatorOptions = Partial<MatPaginatorOptions>;
