import { ChangeDetectorRef, Pipe, PipeTransform } from '@angular/core';
import { ValidationErrors } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';

type CustomErrorData = {
  [key: string]: unknown;
  message?: string;
};

const VALIDATION_MSG_PATH = 'text.validation';
const GENERIC_VALIDATION_MSG = `${ VALIDATION_MSG_PATH }.generic`;

@Pipe({
  name: 'extractValidationError',
  pure: false,
})
export class ExtractValidationErrorPipe implements PipeTransform {

  value = '';

  constructor(
    private translate: TranslateService,
    private ref: ChangeDetectorRef
  ) {
  }

  transform(value: ValidationErrors | null): string {
    if (!value) {
      return '';
    }
    const keys = Object.keys(value);
    if (keys.length > 1) console.error('ExtractValidationErrorPipe: There is more than 1 expected error', value);

    // Normalmente, solo debe haber 1 error en cada momento de la validación
    const [key] = keys.filter(key => !!key?.trim());
    const params = this.getMessageParams(value[key]);
    const message = this.getMessage(key, params);

    this.translate.get(message, params).subscribe(value => this.updateValue(value));
    return this.value;
  }

  /**
   * Returns params as a custom object
   */
  private getMessageParams(params: any): CustomErrorData {
    if (typeof params === 'string') {
      return { message: params };
    } else if (typeof params != 'object') {
      return {};
    }
    return params;
  }

  /**
   * Extracts the untranslated message for the provided key and params
   */
  private getMessage(key: string, params: CustomErrorData) {
    if (params.message) {
      return params.message;
    }
    return key ? `${ VALIDATION_MSG_PATH }.${ key }` : GENERIC_VALIDATION_MSG;
  }

  private updateValue(value: string) {
    if (this.value !== value) {
      this.value = value;
      this.ref.markForCheck();
    }
  }

}
