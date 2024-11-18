import { File } from 'source-map-explorer/lib/types';
import { B64EncodedFile, EncodedFile } from './model';

export const fileToB64 = (file: Blob | File): Promise<B64EncodedFile> => {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    const copy = file as Blob;
    reader.readAsDataURL(copy);
    reader.onload = () => {
      if (reader.result == null) {
        return reject(new Error('Provided file produced no result'));
      }

      const result = reader.result.toString();
      const name = copy instanceof File ? copy.name : 'unknown';
      const [dataUrl, data] = result.split(',');
      const { size, type } = copy;

      resolve({ dataUrl, data, name, size, type });
    };
    reader.onerror = e => reject(e);
  });
};

export const filesToB64 = (files: FileList | Array<Blob | File>): Promise<B64EncodedFile[]> => {
  const list: Promise<B64EncodedFile>[] = [];
  for (let i = 0; i < files.length; i++) {
    const f = files instanceof FileList ? files.item(i) : files[i];
    if (f != null) {
      list.push(fileToB64(f));
    }
  }
  return Promise.all(list);
};

export const b64ToFile = async (encoded: EncodedFile) => {
  const filename = encoded.filename || 'unknown';
  let type = encoded.data.match(/^data:(.+);base64/)?.[1];
  if (!type) {
    type = encoded.type || '';
  }

  const result = await fetch(encoded.data);
  const buffer = await result.arrayBuffer();
  return new File([buffer], filename, { type });
};

export const b64ListToFiles = (encoded: EncodedFile[]) => Promise.all(encoded.map(e => b64ToFile(e)));
