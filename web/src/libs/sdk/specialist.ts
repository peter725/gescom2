import { SimpleModel, StatefulModel } from "@libs/sdk/common";

/**
 * Default user structure.
 */
export interface Specialist extends SimpleModel, StatefulModel {
  name: string;

}

/**
 * Create a new Specialist request structure.
 */
export interface CreateSpecialist {
  id: number | null;
  name: string | null;
}