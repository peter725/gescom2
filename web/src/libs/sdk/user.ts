import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import { AppQuerySource } from '../commons';
import { Module } from './module';
import {Profile } from './profile';
import {Email} from "@libs/sdk/email";
import {Phone} from "@libs/sdk/phone";
import {AutonomousCommunity} from "@libs/sdk/autonomousCommunity";
import {Role} from "@libs/sdk/role";
import {Authority} from "@libs/sdk/authority";

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
  role: Role;
  modules: Module[];
  profile: Profile;
  position: string;
  authority: Authority;
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
  profile: string;
  fullName: string;
  position: string;
  authority: string;
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
  profile: Profile[] | null;
  position: string | null;
  authority: Authority[] | null;
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
  profile?: string;
  state?: number[];
  position?: string;
  authority?: string;
  areaResponsability?: string;
  autonomousCommunity?: string;

}
