import { B64EncodedFile } from '@libs/file';

export interface FileData {
  id              : number;
  createdAt       : string;
  extension       : string;
  documentType    : string;
  size            : number;
  name            : string;
  b64             : B64EncodedFile;
}