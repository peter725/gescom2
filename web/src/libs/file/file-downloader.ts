import { HttpResponse } from '@angular/common/http';
import { DownloadOptions } from './model';


export const downloadLocalFile = (url: string, options?: DownloadOptions) => {
  const anchor = document.createElement('a');
  anchor.href = url;
  if (options?.filename) {
    anchor.download = options.filename;
  }
  anchor.click();
};

export const downloadFile = (data: HttpResponse<any>, options?: DownloadOptions): Blob => {
  const type = data.headers.get('Content-Type') || options?.type || '*/*';
  const blob = new Blob([data.body], { type });
  const url = window.URL.createObjectURL(blob);
  downloadLocalFile(url, options);
  return blob;
};
