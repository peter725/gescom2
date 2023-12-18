import { SimpleModel, TranslatedModel, StatefulModel } from './common';
import { EntityType } from './entity-type';

export interface Entity extends SimpleModel, TranslatedModel, StatefulModel {

  name: string;
  description: string;
  code: string;
  postalCode: string;
  address: string;
  contactPerson: string;
  phone: string;
  email: string;
  rootEntity: boolean;
  parent: Entity;
  entityType: EntityType;
}

export interface EntityForm {
  id: number | null;
  languageId: number | string | null;
  name: string;
  description: string | null;
  parent: Partial<Entity> | null;
  code: string;
  postalCode: string | null;
  address: string | null;
  contactPerson: string | null;
  phone: string | null;
  email: string | null;
  entityType: EntityType | null;
}
