import {
  DataElementListOperand,
  DataElementOperand,
  EvaluableBusinessRule,
  EvaluableExpression,
  EvaluableSimpleExpression,
  ExpressionConnector,
  isElementListOperand,
  isElementOperand,
  isEvaluableLogicalExpression,
  isEvaluableSimpleExpression,
  isValueOperand,
  LogicalOperator,
  ValueOperand,
} from '@libs/sdk/evaluable-business-rules';
import {
  BusinessRuleEvaluator,
  BusinessRuleEvaluation,
  BusinessRuleExpression,
  BusinessRuleExpressionCtx
} from './business-rule.evaluator';


export class BusinessRuleFactory {
  static compileRules(rules: EvaluableBusinessRule[]) {
    return rules.map(rule => this.compileRule(rule));
  }

  static compileRule(rule: EvaluableBusinessRule): BusinessRuleEvaluator {
    const condition = rule.condition
      ? this.compileEvaluation(rule.condition)
      : { evaluate: () => true };
    const verification = this.compileEvaluation(rule.verification);

    return new BusinessRuleEvaluator(rule, condition, verification);
  }

  static compileEvaluation(expression: EvaluableExpression): BusinessRuleEvaluation {
    try {
      if (isEvaluableSimpleExpression(expression)) {
        return {
          evaluate: this.makeExpression(expression)
        };
      }
      // ver como hacer la expresión anidada
      if (isEvaluableLogicalExpression(expression)) {
        const evaluations = expression.expressions.map(expr => this.compileEvaluation(expr));
        return {
          evaluate: (ctx: BusinessRuleExpressionCtx) => {
            return expression.connector === ExpressionConnector.OR
              ? evaluations.some(e => e.evaluate(ctx))
              : evaluations.every(e => e.evaluate(ctx));
          }
        };
      }
    } catch (e) {
      console.error(e);
    }

    return {
      evaluate: this.skippableExpression()
    };
  }

  static makeExpression(expression: EvaluableSimpleExpression): BusinessRuleExpression {
    // No estamos contemplando los que el valor esperado es una palabra reservada tipo:
    // null, currentYear, constant, etc.

    if (
      isElementOperand(expression.targetOperand)
      && isValueOperand(expression.valueOperand)
    ) {
      const operations: Record<string, (expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>) => BusinessRuleExpression> = {};
      operations[LogicalOperator.IS] = this.elementEqualsValue;
      operations[LogicalOperator.IS_EQUAL_TO] = this.elementEqualsValue;
      operations[LogicalOperator.IS_NOT] = this.elementNotEqualsValue;
      operations[LogicalOperator.IS_NOT_EQUAL_TO] = this.elementNotEqualsValue;
      operations[LogicalOperator.IS_GREATER_THAN] = this.elementIsGreaterThanValue;
      operations[LogicalOperator.IS_GREATER_THAN_OR_EQUAL_TO] = this.elementIsGreaterThanValue;
      operations[LogicalOperator.IS_LESS_THAN] = this.elementIsLessThanValue;
      operations[LogicalOperator.IS_LESS_THAN_OR_EQUAL_TO] = this.elementIsLessThanValue;
      operations[LogicalOperator.IS_LIKE] = this.elementIsLikeValue;
      operations[LogicalOperator.IS_NOT_LIKE] = this.elementIsNotLikeValue;
      const op = operations[expression.logicalOperator];
      if (op != null) {
        return op(expression as EvaluableSimpleExpression<DataElementOperand, ValueOperand>);
      }
    } else if (
      isElementListOperand(expression.targetOperand)
      && isValueOperand(expression.valueOperand)
    ) {
      const operations: Record<string, (expr: EvaluableSimpleExpression<DataElementListOperand, ValueOperand>) => BusinessRuleExpression> = {
        unique: this.elementListIsUnique,
        constant: this.elementListIsConstant,
      };
      const op = operations[expression.valueOperand.value];
      if (op != null) {
        return op((expression as EvaluableSimpleExpression<DataElementListOperand, ValueOperand>));
      }
    } else if (
      isElementOperand(expression.targetOperand)
      && isElementOperand(expression.valueOperand)
    ) {
      const operations: Record<string, (expr: EvaluableSimpleExpression<DataElementOperand, DataElementOperand>) => BusinessRuleExpression> = {};
      operations[LogicalOperator.IS] = this.elementsAreEqual;
      operations[LogicalOperator.IS_EQUAL_TO] = this.elementsAreEqual;
      operations[LogicalOperator.IS_NOT] = this.elementsNotEqual;
      operations[LogicalOperator.IS_NOT_EQUAL_TO] = this.elementsNotEqual;
      operations[LogicalOperator.IS_GREATER_THAN] = this.elementIsGreaterThanElement;
      operations[LogicalOperator.IS_GREATER_THAN_OR_EQUAL_TO] = this.elementIsGreaterThanElement;
      operations[LogicalOperator.IS_LESS_THAN] = this.elementIsLessThanElement;
      operations[LogicalOperator.IS_LESS_THAN_OR_EQUAL_TO] = this.elementIsLessThanElement;
      const op = operations[expression.logicalOperator];
      if (op) {
        return op(expression as EvaluableSimpleExpression<DataElementOperand, DataElementOperand>);
      }
    }

    // caso: un elemento y lista de valores
    // caso: una función y un elemento
    // caso: una función y un valor
    // caso: un elemento y una función


    return this.skippableExpression();
  }

  private static skippableExpression(pass = true): BusinessRuleExpression {
    return () => pass;
  }

  private static elementEqualsValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = ctx[targetKey];
      const expectedValue = expr.valueOperand.value;

      return formValue === expectedValue;
    };
  }

  private static elementIsLikeValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = ctx[targetKey];
      const expectedValue = expr.valueOperand.value;

      if (formValue == null) return false;
      return expectedValue.includes(formValue);
    };
  }

  private static elementNotEqualsValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = ctx[targetKey];
      const expectedValue = expr.valueOperand.value;

      return formValue !== expectedValue;
    };
  }

  private static elementIsNotLikeValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = ctx[targetKey];
      const expectedValue = expr.valueOperand.value;

      if (formValue == null) return false;
      return !expectedValue.includes(formValue);
    };
  }

  private static elementIsGreaterThanValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = parseFloat(ctx[targetKey] || '');
      const expectedValue = parseFloat(expr.valueOperand.value);

      if (isNaN(formValue) || isNaN(expectedValue)) return false;

      return LogicalOperator.IS_GREATER_THAN_OR_EQUAL_TO === expr.logicalOperator
        ? formValue >= expectedValue
        : formValue > expectedValue;
    };
  }

  private static elementIsLessThanValue(expr: EvaluableSimpleExpression<DataElementOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const targetKey = expr.targetOperand.element;
      const formValue = parseFloat(ctx[targetKey] || '');
      const expectedValue = parseFloat(expr.valueOperand.value);

      if (isNaN(formValue) || isNaN(expectedValue)) return false;

      return LogicalOperator.IS_LESS_THAN_OR_EQUAL_TO === expr.logicalOperator
        ? formValue <= expectedValue
        : formValue < expectedValue;
    };
  }

  private static elementListIsConstant(expr: EvaluableSimpleExpression<DataElementListOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const values = expr.targetOperand.elements.map(elementName => ctx[elementName] || '');
      const [first] = values;
      return values.every(v => v === first);
    };
  }

  private static elementListIsUnique(expr: EvaluableSimpleExpression<DataElementListOperand, ValueOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const values = expr.targetOperand.elements.map(ctrlName => ctx[ctrlName] || '');
      const unique = new Set(values);
      return values.length !== unique.size;
    };
  }

  private static elementsAreEqual(expr: EvaluableSimpleExpression<DataElementOperand, DataElementOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const formValue = ctx[expr.targetOperand.element];
      const expectedValue = ctx[expr.valueOperand.element];
      return formValue === expectedValue;
    };
  }

  private static elementsNotEqual(expr: EvaluableSimpleExpression<DataElementOperand, DataElementOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const formValue = ctx[expr.targetOperand.element];
      const expectedValue = ctx[expr.valueOperand.element];
      return formValue !== expectedValue;
    };
  }

  private static elementIsGreaterThanElement(expr: EvaluableSimpleExpression<DataElementOperand, DataElementOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const formValue = parseFloat(ctx[expr.targetOperand.element] || '');
      const expectedValue = parseFloat(ctx[expr.valueOperand.element] || '');
      if (isNaN(formValue) || isNaN(expectedValue)) return false;

      return LogicalOperator.IS_GREATER_THAN_OR_EQUAL_TO === expr.logicalOperator
        ? formValue >= expectedValue
        : formValue < expectedValue;
    };
  }

  private static elementIsLessThanElement(expr: EvaluableSimpleExpression<DataElementOperand, DataElementOperand>): BusinessRuleExpression {
    return (ctx: BusinessRuleExpressionCtx) => {
      const formValue = parseFloat(ctx[expr.targetOperand.element] || '');
      const expectedValue = parseFloat(ctx[expr.valueOperand.element] || '');
      if (isNaN(formValue) || isNaN(expectedValue)) return false;

      return LogicalOperator.IS_LESS_THAN_OR_EQUAL_TO === expr.logicalOperator
        ? formValue <= expectedValue
        : formValue > expectedValue;
    };
  }

}
