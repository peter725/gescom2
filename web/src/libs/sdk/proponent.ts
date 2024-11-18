import { SimpleModel, StatefulModel } from "@libs/sdk/common";

/**
 * Default user structure.
 */
export interface Proponent extends SimpleModel, StatefulModel {
  name: string;
}

/**
 * Create a new proponent request structure.
 */
export interface CreateProponent {
  id: number | null;
  name: string | null;
}