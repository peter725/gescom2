import { QueryParamsHandling } from '@angular/router';
import { AppQuery, AppQuerySource } from '@libs/commons';

/**
 * Name acts as a query role identifier
 * Query is just the value
 */
export interface AppliedFilter {
  name: string;
  mode: SearchMode,
  query: AppQuery;
}

/**
 * Filters service configuration
 */
export interface FilterOptions {
  source: AppQuerySource;
  name?: string; // custom name or global
  mode?: SearchMode,
  useParams?: FilterParamsOptions;
}

export interface FilterParamsOptions {
  handling: QueryParamsHandling;
}

export enum SearchMode {
  SEARCH = 's',
  FILTER = 'f',
}

export interface SimpleSearchForm extends AppQuerySource {
  search: string;
  state: number[] | null;
}
