import { FormControl } from '@angular/forms';
import { VTulsaFieldModule } from '@tulsa/libs/sdk/field-module';
import { SampleDataValue } from '@tulsa/libs/sdk/sample';
import { BehaviorSubject } from 'rxjs';


/**
 * Modelo de datos utilizado para
 */
export interface SampleUiField extends VTulsaFieldModule {
  /**
   * Nombre del form control correspondiente en el formulario
   * Este valor será formateado mientras que el original mantiene
   * su valor.
   */
  controlName: string;
  /**
   * Orden del elemento
   */
  order: number;
}

/**
 * Modelo de datos enviado para realizar el cambio de la definición
 * de alguno de los elementos de la interfaz gráfica.
 */
export type SampleUiChange = {
  fieldId: number;
  sectionKey: string;
  groupKey: string;
  rowKey: string;
  change: {
    hidden: boolean,
  }
};

/**
 * Modelos de datos con los campos que permitimos actualizar
 */
export type SampleUiFieldChange = {
  id: number;
  hidden: boolean;
  required: boolean;
};

export type SampleUiPosition = {
  sectionKey: string;
  groupKey: string;
  rowKey: string;
  fieldKey: string;
};

export class SampleUiElement {
  constructor(
    readonly field: SampleUiField,
    readonly formControl: FormControl<SampleDataValue>,
  ) {
  }

  set warning(value: string[]) {
  }

  get elementName() {
    return this.field.elementName;
  }
}

/**
 * Lista de campos de una fila específica
 */
export class SampleUiFieldRow {
  private _visible = new BehaviorSubject<SampleUiElement[]>([]);
  // solo para depurar, luego eliminar
  private _all = new BehaviorSubject<SampleUiElement[]>([]);
  public readonly key: string;
  public readonly order: number;

  constructor(
    private readonly _fields: SampleUiElement[],
  ) {
    const [first] = _fields;
    this.key = first.field.positionRow + '';
    this.order = first.field.positionRow || 0;
    this.updateView();
  }

  get fields() {
    return this._visible.asObservable();
  }

  // solo para depurar, luego eliminar
  get allFields() {
    return this._all.asObservable();
  }

  get length() {
    return this._visible.value.length;
  }

  update(change: SampleUiChange) {
    const id = change.fieldId;
    const index = this._fields.findIndex(f => f.field.id === id);
    if (index === -1) return;

    const copy = { ...this._fields[index] } as SampleUiElement;
    copy.field.hidden = change.change.hidden;

    this._fields.splice(index, 1, copy);
    this.updateView();
  }

  destroy() {
    this._visible.complete();
  }

  private updateView() {
    const update = this._fields
      .filter(v => !v.field.hidden)
      .sort((a, b) => a.field.order - b.field.order);

    this._all.next(this._fields);
    this._visible.next(update);
  }
}

/**
 * Estructura que organiza los campos
 */
export class SampleUiElementGroup {
  private readonly _rows = new BehaviorSubject<SampleUiFieldRow[]>([]);

  constructor(
    private readonly _elements: SampleUiElement[],
    // private readonly fields: SampleUiField[],
    readonly key: string,
    readonly order = 0
  ) {
    this.updateView();
  }

  get rows() {
    return this._rows.asObservable();
  }

  update(change: SampleUiChange) {
    const row = this._rows.value.find(row => row.key === change.rowKey);
    if (row) row.update(change);
  }

  destroy() {
    this._rows.complete();
  }

  private updateView() {
    // const fieldGroups = this.fields.reduce<Record<number, SampleUiField[]>>((acc, field) => {
    const fieldGroups = this._elements.reduce<Record<number, SampleUiElement[]>>((acc, field) => {
      const key = field.field.positionRow;
      if (!acc[key]) acc[key] = [];
      acc[key].push(field);
      return acc;
    }, {});
    const rows = Object.entries(fieldGroups)
      .filter(([, fields]) => fields.some(f => !f.field.hidden))
      .map(([, value]) => new SampleUiFieldRow(value));
    this._rows.next(rows);
  }
}

/**
 * Tipos de secciones posibles
 */
export enum SampleUiSectionType {
  REGULAR = 'REGULAR',
  RESULT = 'RESULT',
}

/**
 * Definición de la estructura base de las secciones del formulario
 */
export abstract class SampleUiSection {
  protected abstract readonly _type: SampleUiSectionType;
  abstract readonly key: string;
  abstract readonly label: string;
  abstract readonly order: number;

  get type() {
    return this._type;
  }

  abstract update(change: SampleUiChange): void;

  abstract destroy(): void;
}

/**
 * Estructura de datos para una sección regular formada por un solo grupo.
 */
export class SampleUiRegularSection extends SampleUiSection {
  protected readonly _type = SampleUiSectionType.REGULAR;

  constructor(
    private readonly _group: SampleUiElementGroup,
    public readonly key: string,
    public readonly label: string,
    public readonly order: number,
  ) {
    super();
  }

  get group() {
    return this._group;
  }

  update(change: SampleUiChange): void {
    this.group.update(change);
  }

  destroy(): void {
    this.group.destroy();
  }
}

/**
 * Estructura que almacena los datos para la sección de resultados
 * */
export class SampleUiResultSection extends SampleUiSection {
  protected readonly _type = SampleUiSectionType.RESULT;
  private readonly _list = new BehaviorSubject<SampleUiElementGroup[]>([]);

  constructor(
    private readonly list: SampleUiElementGroup[],
    public readonly key: string,
    public readonly label: string,
    public readonly order: number,
  ) {
    super();
    this._list.next(list);
  }

  get groups() {
    return this._list.asObservable();
  }

  get length() {
    return this._list.value.length;
  }

  addResults(groups: SampleUiElementGroup[]) {
    const update = [...this._list.value, ...groups];
    this._list.next(update);
  }

  removeResult() {
  }

  update(change: SampleUiChange) {
    const group = this._list.value.find(value => value.key === change.groupKey);
    if (group) group.update(change);
  }

  destroy() {
    this._list.value.forEach(v => v.destroy());
    this._list.complete();
  }
}

/**
 * Estructura que almacena la información de la UI de la muestra
 */
export class SampleUi {
  private readonly _list = new BehaviorSubject<SampleUiSection[]>([]);

  constructor(sections: SampleUiSection[]) {
    const sorted = sections.sort((a, b) => a.order - b.order);
    this._list.next(sorted);
  }

  get sections() {
    return this._list.asObservable();
  }

  update(change: SampleUiChange) {
    const section = this._list.value.find(value => value.key === change.sectionKey);
    if (section) section.update(change);
  }
}
