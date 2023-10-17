import { ValidationErrors } from '@angular/forms';
import { EvaluableBusinessRule } from '@libs/sdk/evaluable-business-rules';

export type BusinessRuleExpressionCtx = {
  [key: string]: string | null;
};

export type BusinessRuleExpression = (ctx: BusinessRuleExpressionCtx) => boolean;

export interface BusinessRuleEvaluation {
  evaluate: BusinessRuleExpression;
}

export class BusinessRule {
  constructor(
    private readonly src: Readonly<EvaluableBusinessRule>,
    private readonly condition: BusinessRuleEvaluation,
    private readonly verification: BusinessRuleEvaluation,
  ) {
  }

  evaluate(ctx: BusinessRuleExpressionCtx): ValidationErrors | null {
    const shouldEvaluate = this.condition.evaluate(ctx);
    if (!shouldEvaluate) {
      return null;
    }

    const passes = this.verification.evaluate(ctx);
    if (passes) {
      return null;
    }
    const error: ValidationErrors = {};
    error[this.src.code] = {
      message: `${ this.src.code }: ${ this.src.infoMessage }`,
    };

    return error;
  }

  get code() {
    return this.src.code;
  }

  get checkedElements() {
    return this.src.checkedElements;
  }

  get verifiedElements() {
    return this.src.verifiedElements;
  }
}
