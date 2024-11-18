import { SimpleModel, TranslatedModel, StatefulModel } from './common';


/**
 * Tipo de visibilidad en la web
 * - Sí
 * - No
 */
export interface VisibilityWeb extends SimpleModel, TranslatedModel, StatefulModel {
  name: string;
}

export interface VisibilityWebForm {
  name: string | null;
}
