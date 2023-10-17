/**
 * By default, models don't have a specific ID
 * the identifier may be a single field called id
 * or a composite value from various fields.
 */
export type BaseModel = Record<string, unknown>;

/**
 * A model with a unique numeric identifier field.
 */
export interface SimpleModel<ID = number> extends BaseModel {
  id: ID;
}

/**
 * A model that has a field containing the language
 * identifier and may have field that specify the
 * code and name.
 */
export interface TranslatedModel {
  languageId: number;
  languageCode?: string;
  languageName?: string;
}

/**
 * A data model may have audit data like the
 * creation/update date and associated user ID.
 */
export interface AuditedModel {
  createdAt?: string;
  updatedAt?: string;
  createdBy?: number;
  updatedBy?: number;
}

/**
 * A model that has a numeric field that contains
 * the state value.
 */
export interface StatefulModel {
  state: number;
  stateName?: string;
}

/**
 * A model that possesses a numeric state id field
 * and a field that describes the state.
 */
export interface StatefulAltModel {
  stateId: number;
  stateName?: string;
}

/**
 * Data model to request a status change.
 */
export interface ReqChangeStatus {
  status: number;
}

export enum ModelStates {
  ON = 1,
  OFF = 2,
}

export const INCLUDE_ALL_STATES: ReadonlyArray<ModelStates> = [ModelStates.ON, ModelStates.OFF];
