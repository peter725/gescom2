import { ErrorDetails } from '@libs/commons';

export interface EvaluableBusinessRules {
  moduleId: number;
  rules: EvaluableBusinessRule[];
}

export interface EvaluableBusinessRule {
  id: number;
  code: string;
  description: string;
  infoMessage: string;
  infoType: string;
  ignoreNull: boolean;
  condition: EvaluableExpression;
  verification: EvaluableExpression;
  checkedElements: string[];
  verifiedElements: string[];
}

export interface EvaluableBusinessRuleForm {
  id: number | null;
  code: string | null;
  description: string | null;
  infoType: string | null;
  infoMessage: string | null;
  ignoreNull: boolean | null;
  condition: EvaluableExpression | null;
  verification: EvaluableExpression | null;
  checkedElements: string[] | null;
  verifiedElements: string[] | null;
}

export interface EvaluableExpression {
  id: number;
  parentId: number;
}

export enum ExpressionConnector {
  AND = 'AND',
  OR = 'OR',
}

export interface EvaluableLogicalExpression extends EvaluableExpression {
  connector: ExpressionConnector;
  expressions: EvaluableExpression[];
}

export enum LogicalOperator {
  IS = 'is',
  IS_NOT = 'isNot',
  IS_EQUAL_TO = 'isEqualTo',
  IS_NOT_EQUAL_TO = 'isNotEqualTo',
  IS_GREATER_THAN = 'isGreaterThan',
  IS_GREATER_THAN_OR_EQUAL_TO = 'isGreaterThanOrEqualTo',
  IS_LESS_THAN = 'isLessThan',
  IS_LESS_THAN_OR_EQUAL_TO = 'isLessThanOrEqualTo',
  IS_IN_LIST = 'isInList',
  IS_NOT_IN_LIST = 'isNotInList',
  HAS_AS_ANCESTOR = 'hasAsAncestor',
  HAS_NOT_AS_ANCESTOR = 'hasNotAsAncestor',
  IS_LIKE = 'isLike',
  IS_NOT_LIKE = 'isNotLike',
}

export interface EvaluableSimpleExpression<T extends Operand = Operand, V extends Operand = Operand> extends EvaluableExpression {
  targetOperand: T;
  logicalOperator: LogicalOperator;
  valueOperand: V;
}

export const isEvaluableLogicalExpression = (obj: EvaluableExpression | EvaluableSimpleExpression | EvaluableLogicalExpression): obj is EvaluableLogicalExpression => {
  if (obj == null) return false;

  const hasConnector = 'connector' in obj ? obj.connector != null : false;
  const hasExpressions = 'expressions' in obj ? Array.isArray(obj.expressions) : false;
  return hasConnector && hasExpressions;
};

export const isEvaluableSimpleExpression = (obj: EvaluableExpression | EvaluableSimpleExpression | EvaluableLogicalExpression): obj is EvaluableSimpleExpression => {
  if (obj == null) return false;

  const hasTarget = 'targetOperand' in obj ? obj.targetOperand != null : false;
  const hasOperator = 'logicalOperator' in obj ? obj.logicalOperator != null : false;
  const hasValue = 'valueOperand' in obj; // El valor es opcional
  return hasTarget && hasOperator && hasValue;
};

export enum OperandType {
  SINGLE_ELEMENT = 'SINGLE_ELEMENT',
  ELEMENT_LIST = 'ELEMENT_LIST',
  DATE_ELEMENTS = 'DATE_ELEMENTS',
  SINGLE_VALUE = 'SINGLE_VALUE',
  VALUE_LIST = 'VALUE_LIST',
  CATALOGUE_ANCESTOR = 'CATALOGUE_ANCESTOR',
  CATALOGUE_TERM = 'CATALOGUE_TERM',
  FUNCTION = 'FUNCTION',
}

export interface Operand {
  type: OperandType;
}

export interface DataElementOperand extends Operand {
  element: string;
}

export interface DataElementListOperand extends Operand {
  elements: string[];
}

export interface ValueOperand extends Operand {
  value: string;
}

export interface ValueListOperand extends Operand {
  value: string;
}

export enum EvaluationInfoType {
  PASS = 'PASS',
  FAIL = 'FAIL',
  WARN = 'WARN',
}

export interface RuleEvaluationError extends ErrorDetails<RuleEvaluationSummary> {
  details: RuleEvaluationSummary;
}

export interface RuleEvaluationSummary {
  description: string;
  infoMessage: string;
  ruleCode: string;
  successful: boolean;
  type: EvaluationInfoType;
}

export const isElementOperand = (obj: Operand): obj is DataElementOperand => obj.type === OperandType.SINGLE_ELEMENT;
export const isElementListOperand = (obj: Operand): obj is DataElementListOperand => obj.type === OperandType.ELEMENT_LIST;
export const isValueOperand = (obj: Operand): obj is ValueOperand => obj.type === OperandType.SINGLE_VALUE;
export const isValueListOperand = (obj: Operand): obj is ValueListOperand => obj.type === OperandType.VALUE_LIST;
