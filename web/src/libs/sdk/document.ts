import {FormControl, FormGroup} from "@angular/forms";

export interface DocumentForm {
  id: number | null;
  documentType: Record<string, any> | null;
  name: string | null;
  extension: string | null;
  base64: string | null;
  requestId: number | null;
}

export interface DocumentsForm {
  id: number | string | null;
  required: Array<DocumentForm> | null
  optional: Array<DocumentForm> | null
  requiredSelect: Array<Record<string, any>> | null
}

export interface SignFile {
  form?: FormGroup;
  file: File;
  b64: string;
  name: string|null;
  documentType: Record<string, any>;
}
