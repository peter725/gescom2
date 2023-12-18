import { AuditedModel, SimpleModel, StatefulModel } from './common';


/**
 * Asociación de campo-módulo que define la condición para que el campo asociado
 * se muestre en pantalla o no.
 *
 * Si el parent field tiene el valor definido, debe aparecer el field relacionado
 * y su validación puede cambiar a ser required.
 */
export interface FieldModuleAssociation extends SimpleModel, AuditedModel, StatefulModel {
  /* @deprecated */
  parentFieldModuleId: number;
  /* @deprecated */
  value: string;
  /* @deprecated */
  fieldModuleId: number;
  /* @deprecated */
  required: boolean;

  sourceFieldModuleId: number;
  sourceExpectedValue: string;
  targetFieldModuleId: number;
  targetElementName: string;
  targetIsRequired: boolean;
}

export interface VFieldModuleAssociation extends SimpleModel {
  sourceFieldModuleId: number;
  sourceElementName: string;
  sourceExpectedValue: string;

  targetFieldModuleId: number;
  targetElementName: string;
  targetIsRequired: boolean;
}
