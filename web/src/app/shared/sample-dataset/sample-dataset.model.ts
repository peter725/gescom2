import { FormArray, FormControl, FormGroup, } from '@angular/forms';
import { ValidationError } from '@nrwl/angular/src/generators/ng-add/utilities/types';
import { GeneratedCode } from '@tulsa/app/shared/catalogue-browser/models';
import { BusinessRuleEvaluator } from '@tulsa/app/shared/sample-business-rule';
import { DatasetGroupContainerUI } from '@tulsa/app/shared/sample-dataset/sample-dataset';
import { FieldElementType } from '@tulsa/libs/sdk/field-module';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { TulsaModule } from '@tulsa/libs/sdk/module';
import { TulsaSampleSeason } from '@tulsa/libs/sdk/sample-season';
import { TulsaScopeView } from '@tulsa/libs/sdk/scope';
import { Observable } from 'rxjs';


export type DatasetLoadType = 'sample' | 'template' | 'programming';

export type DatasetLoadOptions = {
  type: DatasetLoadType;
  id?: number;
  templateId?: number;
  programmingId?: number;
  // definir type sample | template | programming
  // definir templateId para cargar muestra + template
  // definir programmingId para cargar muestra + programming
};

export enum DatasetContainerType {
  COMMON = 'C', // Campos comunes del formulario
  RESULT = 'R', // Resultados del boletín analítico
}

export interface DatasetContainerOpts<T = unknown> {
  order?: number,
  index?: number,
  position?: string,
  label?: string,
  items?: T[],
}

export interface DatasetContainerGroupOpts<T = unknown> extends DatasetContainerOpts<T> {
  formGroup: FormGroup<DatasetFormGroup>;
  containerType: DatasetContainerType;
}

export interface DatasetContainerColumnOpts<T extends DatasetFormValue> {
  formCtrl: FormControl<T>;
  elementName: string;
  elementType: FieldElementType;

  label?: string;
  description?: string;
  position?: string;
  order?: number;
  index?: number;
  hidden?: boolean;

  // Si la columna es combo
  comboValues?: TulsaFieldModuleTerm[],
  comboMultiselect?: boolean;

  // Si la columna es catálogo
  catalogueId?: number;
  hierarchyId?: number;
}

export type DatasetContainer = {
  common: Observable<DatasetGroupContainerUI[]>;
  result: Observable<DatasetGroupContainerUI[]>;
};

export interface DatasetContainerUI {
  update(opts: DatasetColumnOpts): void;

  destroy(): void;

  setError(error: ValidationError): void;

  onHidden: Observable<DatasetHideChangeEv>;
  isHidden: boolean;

  order: number;
  index: number;
  /**
   * Código que ubica el contenedor.
   * Sigue el formato "ContainerType#GroupIdx#positionRow#elementName".
   */
  position: string;
  label: string;
}

export interface DatasetHideChangeEv {
  position: string;
  hidden: boolean;
}

export interface DatasetReorganizeOpts {
  index: number;
  position: string;
}

export interface DatasetColumnOpts {
  targetPosition: string;

  hidden?: boolean;
  required?: boolean;
  comboOptions?: TulsaFieldModuleTerm[];
  comboMultiselect?: boolean;
}

export type DatasetForm = {
  common: FormGroup<DatasetFormGroup>;
  result: FormArray<FormGroup<DatasetFormGroup>>;
}

export type DatasetFormGroup = {
  [key: string]: FormControl<DatasetFormValue>;

  // position: FormControl<string>;
  datasetId: FormControl<number | null>;
  resultId: FormControl<number | null>;
};

export type DatasetFormValue =
  string
  | number
  | TulsaFieldModuleTerm
  | GeneratedCode
  | Array<string | TulsaFieldModuleTerm>
  | null;

export const DEFAULT_VALUE_SEPARATOR = '$';

export type BusinessRulesGroups = {
  // Reglas que aplican sobre un solo campo concreto
  single: Record<string, BusinessRuleEvaluator[]>,
  // Reglas que aplican sobre un grupo específico
  // en este caso el grupo común
  common: BusinessRuleEvaluator[],
  // Reglas que aplican sobre un grupo específico
  // en este caso el grupo de resultado
  result: BusinessRuleEvaluator[],
  // Reglas que aplican sobre el dataset completo
  global: BusinessRuleEvaluator[],
};

export interface SamplePageConfigs {
  module: TulsaModule | null;
  season: TulsaSampleSeason | null;
  scope: TulsaScopeView | null;
}

export interface SampleConfig extends SamplePageConfigs {
  templateSrcId?: number;
  programSrcId?: number;
}

export interface SampleTemplateConfig extends SamplePageConfigs {
  templateName: string | null;
  sharedAccess: boolean;
}

export interface SampleProgrammingConfig extends SamplePageConfigs {
  recordCount: number;
  parent: unknown | null;
}
