import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import { AppQuerySource } from '../commons';
import { Module } from './module';
import {Profile } from './profile';
import {Email} from "@libs/sdk/email";
import {Phone} from "@libs/sdk/phone";
import {AuthorityOEU} from "@libs/sdk/AuthorityOEU";
import {AutonomousCommunity} from "@libs/sdk/AutonomousCommunity";

/**
 * Default user structure.
 */
export interface User extends SimpleModel, StatefulModel {

  name: string;
  firstSurname: string;
  secondSurname: string;
  fullName: string;
  autonomousCommunity: AutonomousCommunity;
  nif: string;
  emails: Email[];
  phone: Phone[];
  role: string;
  modules: Module[];
  profile: Profile;
  position: string;
  authorityOEU: AuthorityOEU;
  generalDirection: string;
  areaResponsability: string;


  //multiScope: false;
  //scopes: string[];
}

export interface UserView extends SimpleModel, StatefulAltModel {
  nif: string;
  name: string;
  firstSurname: string;
  secondSurname: string;
  emails: Email[];
  phones: Phone[];
  autonomousCommunity: string;
  provinces: string;
  entities: string;
  modules: string;
  profile: string;
  fullName: string;
  position: string;
  authorityOEU: string;
  generalDirection: string;
  areaResponsability: string;
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
  emails: Email[] | null;
  phones: Phone[] | null;
  autonomousCommunity: AutonomousCommunity[] | null;
  role: string | null;
  profile: Profile[] | null;
  modules: Module[] | null;
  position: string | null;
  authorityOEU: AuthorityOEU | null;
  generalDirection: string | null;
  areaResponsability: string | null;

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
  emails?: string[];
  phone?: string[];
  role?: string;
  modules?: string[];
  profile?: string;
  state?: number[];
  position?: string;
  authorityOEU?: string;
  generalDirection?: string;
  areaResponsability?: string;
  autonomousCommunity?: string;

}
