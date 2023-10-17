import { AbstractControl, AsyncValidatorFn, FormControl, FormGroup } from '@angular/forms';
import { SampleDataGroup, SampleFormData } from '@tulsa/app/shared/sample/models';
import { SampleUiField } from '@tulsa/app/shared/sample/sample-ui';
import { SampleDataValue } from '@tulsa/libs/sdk/sample';
import { BusinessRule, BusinessRuleExpressionCtx } from './business-rule';
import { simplifyValue } from './utils';


export class BusinessRulesValidations {
  static validateGlobal(rule: BusinessRule, fields: Record<string, SampleUiField>): AsyncValidatorFn {
    return (ac: AbstractControl) => new Promise(resolve => {
      if (!ac || !(ac instanceof FormGroup)) {
        return resolve(null);
      }
      const group = ac as FormGroup<SampleFormData>;

      const commonCtx = Object.entries(group.controls.data.value).reduce<BusinessRuleExpressionCtx>((acc, [key, value]) => {
        acc[key] = simplifyValue(value, fields[key]);
        return acc;
      }, {});
      group.controls.results.controls.forEach(ctrl => {
        const resultCtx = Object.entries(ctrl.value.data || {}).reduce<BusinessRuleExpressionCtx>((acc, [key, value]) => {
          acc[key] = simplifyValue(value, fields[key]);
          return acc;
        }, {});

        const ctx = { ...commonCtx, ...resultCtx };
        const result = rule.evaluate(ctx);
        const ctrlName = rule.verifiedElements.find(ctrlName => !fields[ctrlName].hidden);
        if (ctrlName) {
          let errorPosition = group.controls.data.get(ctrlName);
          if (!errorPosition) errorPosition = ctrl.controls.data.get(ctrlName);
          if (errorPosition) errorPosition.setErrors(result);
        }
      });
      resolve(null);
    });
  }

  static validateGroup(rule: BusinessRule, fields: Record<string, SampleUiField>): AsyncValidatorFn {
    return (ac: AbstractControl) => new Promise(resolve => {
      if (!ac || !(ac instanceof FormGroup)) {
        return resolve(null);
      }
      const group = ac as FormGroup<SampleDataGroup>;

      const ctx = Object.entries(group.value).reduce<BusinessRuleExpressionCtx>((acc, [key, value]) => {
        acc[key] = simplifyValue(value, fields[key]);
        return acc;
      }, {});
      const result = rule.evaluate(ctx);
      const ctrlName = rule.verifiedElements.find(ctrlName => !fields[ctrlName].hidden);
      if (ctrlName && result) {
        const errorPosition = group.get(ctrlName);
        if (errorPosition) errorPosition.setErrors(result);
      }

      resolve(result || null);
    });
  }

  static validateCtrl(rule: BusinessRule, field: SampleUiField): AsyncValidatorFn {
    return (ac: AbstractControl) => new Promise(resolve => {
      if (!ac) {
        return resolve(null);
      }
      const ctrl = ac as FormControl<SampleDataValue>;
      const [singleElm] = rule.checkedElements;

      const ctx: BusinessRuleExpressionCtx = {};
      ctx[singleElm] = simplifyValue(ctrl.value, field);

      resolve(rule.evaluate(ctx));
    });
  }

}
