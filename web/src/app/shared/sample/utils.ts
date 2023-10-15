import { SampleUiField } from '@tulsa/app/shared/sample/sample-ui';
import { FieldPositionType, StaticFieldNames, VTulsaFieldModule } from '@tulsa/libs/sdk/field-module';
import { SampleDataValue } from '@tulsa/libs/sdk/sample';
// import { SampleUIDataField, SampleUIDataGroup, SampleUIDataRow, SampleUISection } from './models';

export const LIST_SECTION_KEYS = [FieldPositionType.RESULTADOS];
export const SAFE_RESULT_COLS = [
  StaticFieldNames.RESULT_ID, StaticFieldNames.EVAL_CODE, StaticFieldNames.PARAM_NAME, StaticFieldNames.PARAM_TYPE
];
export const DEFAULT_SEPARATOR = '$';
export const FIELDS_DISABLED_WHEN_VALUE = [StaticFieldNames.SAMPLE_ID, StaticFieldNames.RESULT_ID];

export const formatControlName = (elementName: string) => elementName.replace('.', '$');

/**
 *
 * */
/*export const buildSampleUISection = (field: VTulsaFieldModule, order: number): SampleUISection => ({
  code: field.positionType,
  label: field.positionName,
  cssClass: 'mb-5',
  hidden: false,
  order,
  type: FieldPositionType.RESULTADOS === field.positionType ? 'result' : 'data',
  items: [],
});*/

/**
 *
 */
/*export const buildSampleUIDataGroup = (field: VTulsaFieldModule, order: number): SampleUIDataGroup => ({
  code: `group-${ field.positionType }`,
  label: '',
  cssClass: '',
  hidden: false,
  order,
  items: [],
});*/

/*export const buildSampleUIDataRow = (field: VTulsaFieldModule): SampleUIDataRow => ({
  code: `row-${ field.positionRow }`,
  label: '',
  cssClass: '',
  hidden: false,
  order: field.positionRow,
  items: [],
});*/

export const buildSampleUIDataField = (field: VTulsaFieldModule): SampleUiField => ({
  ...field,
  controlName: formatControlName(field.elementName),
  order: field.positionColumn,
});

// export const getFieldDisabledState = (field: VTulsaFieldModule, value: SampleDataValue, defaultValue: SampleDataValue): boolean => {
//   const hasDefaultValue = !!defaultValue;
//   if (hasDefaultValue) {
//     return true;
//   }
//
//   const hasValue = !!value;
//   const shouldDisableIfValue = FIELDS_DISABLED_WHEN_VALUE.includes(field.elementName);
//   if (hasValue && shouldDisableIfValue) {
//     return true;
//   }
//   // condiciones adicionales
//   return false;
// };

export const simplifyValue = (currentValue: SampleDataValue, field: SampleUiField): string | null => {
  if (currentValue == null) {
    return null;
  }

  if (Array.isArray(currentValue)) {
    const separator = field?.valueSeparator || DEFAULT_SEPARATOR;
    return currentValue.map(v => simplifyValue(v, field)).join(separator);
  }

  if (typeof currentValue === 'object') {
    return simplifyValue(currentValue.code, field);
  }

  return currentValue.toString();
};

export const expandValue = () => {};
