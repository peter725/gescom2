import { SimpleModel, StatefulAltModel, StatefulModel } from "@libs/sdk/common";
import { Profile } from "@libs/sdk/profile";
import { AppQuerySource } from "@libs/commons";

export interface UserType extends SimpleModel, StatefulModel {

  name: string;
  profile: Profile;
}

export interface UserTypeView extends SimpleModel, StatefulAltModel {

  name: string;
  profile: string;

}

export interface CrateUserType{

  id: number | null;
  name: string | null;

  profile: Profile | null;
}

export interface UserFilterForm extends AppQuerySource {

  name?: string;
  profile?: string;
}