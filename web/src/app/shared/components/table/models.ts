import { Observable } from 'rxjs';

export interface TableColumnDef {
  /**
   * Column name, used as a column identifier and as a title.
   * If no property or getterFn is provided, name will be used as an alternative to property.
   */
  name: string;
  /**
   * Full path to access the JSON's value.
   */
  property?: string;
  /**
   * Column data type
   */
  type: TableDataDef;

  /**
   * How the data type should be rendered in a certain way when is not required to use a custom template
   * E.g. DATE type would use 'yyyy-mm-dd'
   */
  format?: string;

  /**
   * Function to extract the value from the complex object if needed
   */
  getterFn?: <T>(val: T) => string | number | Observable<string | number>;

  sticky: boolean;
}

export enum TableDataDef {
  DEFAULT,
  DATE,
  LINK,
  PHONE,
  EMAIL,
}
