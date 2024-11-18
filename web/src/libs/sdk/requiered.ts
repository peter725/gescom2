import { SimpleModel, TranslatedModel, StatefulModel } from './common';


/**
 * Tipo de obligatoriedad del campo
 * - Obligatorio
 * - Opcional
 * - Dependiente
 * - No aplica
 */
export interface Required extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
}

export enum RequiredCodes {
  MANDATORY = 1,
  OPTIONAL = 2,
  DEPENDENCY = 3,
}

export interface RequiredForm {
  name: string | null;
}
