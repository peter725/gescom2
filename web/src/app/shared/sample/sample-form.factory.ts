import { FormControl, FormGroup, Validators } from '@angular/forms';
import { simplifyValue, DEFAULT_SEPARATOR } from './utils';
import { DefaultValuesCodes, FieldElementType } from '@tulsa/libs/sdk/field-module';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { SampleDataValue, TulsaSampleResult } from '@tulsa/libs/sdk/sample';
import { SimpleUidGenerator } from '@tulsa/libs/utils/uid';
import { SampleDataGroup, SampleFormResult } from './models';
import { SampleUiField } from './sample-ui';


type FieldWithValue = {
  field: SampleUiField,
  currentValue: string | undefined
};

export class SampleFormFactory {

  constructor(
    private readonly comboValues: Record<string, TulsaFieldModuleTerm[]>,
  ) {
  }

  formControl(currentValue: string | undefined, field: SampleUiField): FormControl<SampleDataValue> {
    let value = this.expandValue(currentValue, field);
    if (!value) {
      value = this.defaultValue(field.valueDefault);
    }
    const disabled = false; // puede ser condicional
    const ctrl = new FormControl({ value, disabled, });
    if (field.required) {
      ctrl.setValidators(Validators.required);
    }
    return ctrl;
  }

  formGroup(fieldWithValue: FieldWithValue[] = []): FormGroup<SampleDataGroup> {
    return fieldWithValue.reduce((acc, { field, currentValue }) => {
      acc.addControl(field.controlName, this.formControl(currentValue, field));
      return acc;
    }, new FormGroup<SampleDataGroup>({}));
  }

  resultGroup(result: TulsaSampleResult, fieldWithValue: FieldWithValue[] = []): FormGroup<SampleFormResult> {
    return new FormGroup<SampleFormResult>({
      id: new FormControl(result.id),
      sampleId: new FormControl(result.sampleId || null),
      groupKey: new FormControl<string>(SimpleUidGenerator.createUid(), { nonNullable: true }),
      data: this.formGroup(fieldWithValue),
    });
  }

  simplifyValue(currentValue: SampleDataValue, field: SampleUiField): string | null {
    return simplifyValue(currentValue, field);
  }

  expandValue(currentValue: string | undefined, field: SampleUiField): SampleDataValue {
    if (currentValue == null) return null;

    let value: SampleDataValue = currentValue;
    let valueAsList: string[] = [];
    const separator = field.valueSeparator || DEFAULT_SEPARATOR;

    if (field.elementType === FieldElementType.COMBO) {
      const srcList = (this.comboValues[field.elementName] || []);
      if (field.multiple) {
        valueAsList = currentValue.split(separator);
        value = srcList.filter(v => valueAsList.includes(v.code));
      } else {
        value = srcList.find(v => v.code === currentValue);
      }
    }

    // if (finalValue && typeof finalValue === 'string' && field.elementName === StaticFieldNames.SAMPLE_ID) {
    // Es un caso especial que mostramos menos información de lo que estamos recibiendo
    // para que el usuario no tenga control sobre el prefijo del código (de momento)
    // const parts = finalValue.split('_');
    // if (parts.length > 3) {
    //   finalValue = parts.slice(3).join('');
    // }
    // }

    return value;
  }

  defaultValue(currentValue: string | null): SampleDataValue {
    let defaultValue: string | null;
    switch (currentValue) {
      case DefaultValuesCodes.YEAR:
        defaultValue = `${ new Date().getFullYear() }`;
        break;
      case DefaultValuesCodes.PREV_YEAR:
        // Remplazar por el año de la campaña
        defaultValue = `${ new Date().getFullYear() - 1 }`;
        break;
      default:
        defaultValue = currentValue;
    }
    return defaultValue;
  }
}
