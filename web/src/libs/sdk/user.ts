import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import { AppQuerySource } from '../commons';

/**
 * Default user structure.
 */
export interface User extends SimpleModel, StatefulModel {

  name: string;
  firstSurname: string;
  secondSurname: string;
  fullName: string;

  nif: string;
  email: string;
  phone: string;

  //modules: Module[];
  //profile: Profile;

  //multiScope: false;
  //scopes: string[];
}

export interface UserView extends SimpleModel, StatefulAltModel {
  nif: string;
  name: string;
  firstSurname: string;
  secondSurname: string;
  email: string;
  ccaa: string;
  provinces: string;
  //entities: string;
  //modules: string;
  //profile: string;
  fullName: string;
}

/**
 * Create a new user request structure.
 */
export interface CreateUser {
  id: number | null;

  name: string | null;
  firstSurname: string | null;
  secondSurname: string | null;
  nif: string | null;
  email: string | null;
  //phone: string | null;

  //profile: TulsaProfile[] | null;
 // modules: TulsaModule[] | null;
}

/**
 * Update user request structure.
 */
// export interface ReqUpdateUser extends SimpleModel, TulsaCreateUser {
// modules: TulsaModule[];
// profile: TulsaProfile;
// }

export interface UserFilterForm extends AppQuerySource {
  name?: string;
  firstSurname?: string;
  secondSurname?: string;
  nif?: string;
  email?: string;
  phone?: string;
  state?: number[];
}
