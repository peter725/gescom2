import { B64EncodedFile } from '@libs/file';

export interface FileData {
  id              : number;
  date            : string;
  type            : string;
  description     : string;
  b64             : B64EncodedFile;

}