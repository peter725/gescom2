/* import { TulsaCCAA } from './ccaa'; */
import { BaseModel, SimpleModel, StatefulAltModel, StatefulModel } from './common';
import { Entity } from './entity';
/* import { TulsaProvince } from './province'; */


export interface Scope extends BaseModel, StatefulModel {
  user: number;
  entity: Entity;
}

export interface ScopeView extends SimpleModel<string>, StatefulAltModel {
  email: string;
  entity: number;
  entityName: string;
  isoCode: string;
  languageName: string;
  name: string;
  nif: string;
  phoneNumber: string;
  scopeCode: string;
  stateId: number;
  stateName: string;
  surname2: string;
  surname: string;
  user: number;
}

export interface ScopeForm {
  user: number | null;
  entity: Partial<Entity> | null;
}

export type ScopeIdSrc = Scope | ScopeForm | ScopeView | Partial<Scope | ScopeForm | ScopeView>;

/**
 * Creates a formatted ID from the provided ScopeIdSrc
 */
export const createScopeId = (scope: ScopeIdSrc) => {
  const { user, entity } = scope;
  const userId = user || 0;
/*   const ccaaId = ccaa == null ? 0 : typeof ccaa === 'number' ? ccaa : ccaa.id;
  const provinceId = province == null ? 0 : typeof province === 'number' ? province : province.id; */
  const entityId = entity == null ? 0 : typeof entity === 'number' ? entity : entity.id;
  
  return `${ userId },${ entityId }`;
};
