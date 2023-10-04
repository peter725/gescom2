/**
 * Data structure that holds all the necessary data to display a column.
 */
import { BehaviorSubject, isObservable, Observable, of } from 'rxjs';

export interface ColumnDef {
  /**
   * Column name that should be used to reference this column.
   */
  name: string;
  /**
   * Accessor property name that should be used to display the value. If this
   * property is not set, the value will default to the name property.
   */
  property: string;
  /**
   * Text label that should be used for the column header. If this property
   * is not set, the header text will default to the column name.
   */
  label: string;
  /**
   * Longer text label that should be used for the column title on hover.
   * If this property is not set, the header text will default to the column
   * label.
   */
  extLabel: string;
  /**
   * Alignment of the cell values.
   */
  align: 'start' | 'center' | 'end';
  /**
   * Whether the column is sortable
   */
  sorteable: boolean;
  /**
   * Sort property name that should be used if the column is sorteable. If
   * this property is not set, the value will default to the name property.
   */
  sortProperty: string;
  /**
   * Whether the colum is visible
   */
  visible: boolean;
  /**
   * Defines the order where the column is displayed
   */
  order: number;
  /**
   * Accessor function to retrieve the data should be provided to the cell.
   * If this property is not set, the data cells will assume that the column
   * name is the same as the data property the cells should display.
   */
  dataAccessor?: <T = any>(data: T, property: string) => string;

  // TBA - if necessary
  // type: 'text' | 'numeric' | 'mail' | 'phone';
  // format: any;
  // width: number | string | idk // defines column width as css or number of pixels?
}

/**
 * Data structure alias used as a base to generate a full ColumnDef
 */
export type ColumnSrc = Partial<ColumnDef> | string;

/**
 * Builds columns definitions based on the provided source.
 * Optional config allows to configure defaults
 */
export const buildColumnDefs = (
  src: ColumnSrc[],
  config: Partial<{
    align: 'start' | 'center' | 'end',
    sorteable: boolean,
    visible: boolean,
    dataAccessor: <T = any>(data: T, property: string) => string,
  }> = {},
): ColumnDef[] => {
  const columns: ColumnDef[] = [];

  // Default values
  const align = config.align || 'start';
  const sorteable = config.sorteable || false;
  const dataAccessor = config.dataAccessor || undefined;
  const visible = config.visible != null ? config.visible : true;

  for (let i = 0; i < src.length; i++) {
    const col = src[i];
    const name = typeof col === 'string' ? col : col.name;
    if (!name) {
      break;
    }

    const order = i;
    let column: ColumnDef;
    if (typeof col === 'string') {
      column = {
        name,
        property: name,
        label: name,
        extLabel: name,
        align: align,
        sorteable: sorteable,
        sortProperty: name,
        visible,
        order,
        dataAccessor,
      };
    } else {
      column = {
        name,
        property: col.property || name,
        label: col.label || name,
        extLabel: col.extLabel || name,
        align: col.align || align,
        sorteable: col.sorteable || sorteable,
        sortProperty: col.sortProperty || name,
        visible: col.visible != null ? col.visible : visible,
        order: col.order != null ? col.order : order,
        dataAccessor: col.dataAccessor || dataAccessor,
      };
    }
    columns.push(column);
  }
  return columns;
};

/**
 * Extracts a value from the provided source data based on the provided path.
 * Return value may be any javascript primitive.
 */
export const extractProperty = (data: any, path: string): any => {
  const paths = path.split('.');
  if (!paths.length || !data) {
    return '';
  }

  if (!(typeof data === 'object')) {
    return data;
  }

  if (paths.length === 1) {
    return data[paths[0]];
  }
  const property = paths[0];
  paths.shift();
  path = paths.join('.');
  if (data.hasOwnProperty(property)) {
    return extractProperty(data[property], path);
  }
  return '';
};

export class ColumnSource {
  private readonly columnNames = new BehaviorSubject<string[]>([]);
  private readonly activeColumns = new BehaviorSubject<ColumnDef[]>([]);
  private defs: ColumnDef[] = [];

  constructor(private readonly src: ColumnSrc[] | Observable<ColumnSrc[]>) {
    this.generateColumnDefs();
  }

  /**
   * Visible columns length
   */
  get length(): number {
    // this.updateColumnList();
    return this.columnNames.value.length;
  }

  /**
   * Returns the list of the active columns.
   */
  get columns() {
    // if (!this.cols.value.length) {
    //   this.updateColumnList();
    // }
    return this.columnNames.asObservable();
  }

  /**
   * Returns the list of active columns only once.
   */
  get columnsInstant() {
    // if (!this.cols.value.length) {
    //   this.updateColumnList();
    // }
    return [...this.columnNames.value];
  }

  get columnDefs$() {
    return this.activeColumns.asObservable();
  }

  getColumnDefs() {
    return this.defs;
  }

  getColumnDef(col: number | string) {
    const found = this.findColumn(col);
    if (!found) {
      throw new Error(`Column [${ col }] does not exist in the defined columns`);
    }
    return found;
  }

  /**
   * Shows a column
   */
  show(col: number | string) {
    const found = this.findColumn(col);
    if (found) {
      found.visible = true;
      this.updateColumnList();
    }
  }

  /**
   * Hides columns
   */
  hide(col: number | string) {
    const found = this.findColumn(col);
    if (found) {
      found.visible = false;
      this.updateColumnList();
    }
  }

  toggle(col: number | string) {
    const found = this.findColumn(col);
    if (found) {
      found.visible = !found.visible;
      this.updateColumnList();
    }
  }

  reorder(cols: string[]) {
    for (let i = 0; i < cols.length; i++) {
      const found = this.findColumn(cols[i]);
      if (found) found.order = i;
    }
    this.updateColumnList();
  }

  updateVisible(cols: string[]) {
    for (let i = 0; i < this.defs.length; i++) {
      const def = this.defs[i];
      def.visible = cols.includes(def.name);
    }
    this.updateColumnList();
  }

  reset() {
    console.info('resetting');
    this.generateColumnDefs();
  }

  private generateColumnDefs() {
    const loader = isObservable(this.src) ? this.src : of(this.src);
    loader.subscribe(cols => {
      const defs = buildColumnDefs(cols, { dataAccessor: extractProperty });
      this.defs = [...defs];
      this.updateColumnList();
    });
  }

  /**
   * Updates the column list based on the updated definition
   */
  private updateColumnList() {
    const update = this.defs
      .filter(v => v.visible)
      .sort((a, b) => a.order > b.order ? 1 : -1);
    this.columnNames.next(update.map(v => v.name));
    this.activeColumns.next(update);
  }

  /**
   * Finds a column based on the provided name or index.
   */
  private findColumn(col: number | string) {
    if (typeof col === 'string') {
      // Provided col is the name.
      return this.defs.find(v => v.name === col);
    } else {
      // Provided col is an index.
      const length = this.defs.length;
      return col > -1 && col < length
        ? this.defs[col]
        : undefined;
    }
  }

}
