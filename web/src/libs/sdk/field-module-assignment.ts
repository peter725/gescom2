import { SimpleModel, StatefulModel } from '@libs/sdk/common';

export interface FieldModuleAssignment extends SimpleModel, StatefulModel {

}

export interface VFieldModuleAssignment extends SimpleModel {
  moduleId: number;
  moduleCode: string;

  sourceFieldId: number;
  sourceElementName: string;

  targetFieldId: number;
  targetElementName: string;
}
