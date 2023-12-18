import { SimpleModel, TranslatedModel, StatefulModel } from './common';


/**
 * Tipo de acceso al dato
 * - Público
 * - Privado
 */
export interface Visibility extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
}

export interface VisibilityForm {
  name: string | null;
}
