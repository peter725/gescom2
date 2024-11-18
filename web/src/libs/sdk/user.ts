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
  Surname: string;
  lastSurname: string;
  fullName: string;
  autonomousCommunity: AutonomousCommunity;

  dni: string;
  email: string;
  phone: string;

  modules: Module[];
  role: Role;

  multiScope: false;
  scopes: string[];

}

export interface UserView extends SimpleModel, StatefulAltModel {
  dni: string;
  name: string;
  Surname: string;
  lastSurname: string;
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
  surname: string | null;
  lastSurname: string | null;
  dni: string | null;
  email: string | null;
  phone: string | null;

  userType: UserType[] | null;
  role: Role[] | null;
  modules: Module[] | null;
  autonomousCommunity: AutonomousCommunity | null;

}

/**
 * Update user request structure.
 */

export interface UserFilterForm extends AppQuerySource {
  name?: string;
  surname?: string;
  lastSurname?: string;
  dni?: string;
  email?: string;
  phone?: string;
  profile?: string;
  state?: number[];

}
