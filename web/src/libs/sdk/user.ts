import { SimpleModel, StatefulAltModel, StatefulModel } from './common';
import { AppQuerySource } from '../commons';
import { Module } from './module';
import {Profile } from './profile';
import {Email} from "@libs/sdk/email";
import {Phone} from "@libs/sdk/phone";
import {AutonomousCommunity} from "@libs/sdk/autonomousCommunity";
import {Role} from "@libs/sdk/role";
import {Authority} from "@libs/sdk/authority";
import { UserType } from "@libs/sdk/userType";

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
  email: string;
  phone: string;

  modules: Module[];
  profile: Profile;

  multiScope: false;
  scopes: string[];

}

export interface UserView extends SimpleModel, StatefulAltModel {
  nif: string;
  name: string;
  firstSurname: string;
  secondSurname: string;
  email: string;
  autonomousCommunity: string;
  provinces: string;
  entities: string;
  modules: string;
  profile: string;
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
  phone: string | null;
  password: string | null;

  userType: UserType[] | null;
  profile: Profile[] | null;
  modules: Module[] | null;
  autonomousCommunity: AutonomousCommunity | null;

}

/**
 * Update user request structure.
 */

export interface UserFilterForm extends AppQuerySource {
  name?: string;
  firstSurname?: string;
  secondSurname?: string;
  nif?: string;
  email?: string;
  phone?: string;
  profile?: string;
  state?: number[];

}
