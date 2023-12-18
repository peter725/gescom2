import { SimpleModel, StatefulModel} from './common';

export interface Profile extends SimpleModel, StatefulModel {
  name: string;
}

export interface ProfileForm {
  id: number | null;
  name: string | null;
}
