export * from './button';
export * from './error';
export * from './form';
export * from './status';


/**
 *  Angular's {@link HttpParams} value type placeholder.
 */
export type HttpParamsSource = string | number | boolean | ReadonlyArray<string | number | boolean>;

export type QueryScope = '';

export interface AppQueryItem {
  scope: QueryScope;
  value: HttpParamsSource;
}

/**
 * Query values source
 */
export interface AppQuerySource {
  [key: string]: HttpParamsSource | null | undefined;
}

/**
 * Generic query
 */
export interface AppQuery {
  getSource(): AppQuerySource;

  toObject(): Record<string, HttpParamsSource>;
}

export type TranslationParams = Record<string, unknown> | undefined;

export enum OperationMode {
  CONTAINS = 'ct',
  EQUALS = 'eq',
  NOT_EQUALS = 'ne',
  STARTS_WITH = 'st',
  ENDS_WITH = 'ed',
  GREATER = 'gt',
  GREATER_OR_EQUAL = 'gte',
  LESSER = 'lt',
  LESSER_OR_EQUAL = 'lte',
}

export const  FORMATTED_QUERY = /.+(__)(\w){2,3}$/;

export const OPERATION_SEPARATOR = '__';
