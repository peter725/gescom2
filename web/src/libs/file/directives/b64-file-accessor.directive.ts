import { Directive } from '@angular/core';
import { filesToB64 } from '@libs/file';
import { B64EncodedFile } from '../model';
import { BaseFileInputAccessorDirective } from '@libs/file';

/**
 * Links a file input b64 converted value with its respective NgControl value
 * Expected control value is B64EncodedFile or a list of B64EncodedFile.
 */
@Directive({
  selector: 'input[appB64FileAccessor][type=file]',
})
export class B64FileAccessorDirective extends BaseFileInputAccessorDirective<B64EncodedFile | Array<B64EncodedFile>> {
  protected async handleInput(list: FileList) {
    if (!list.length) {
      this._onChangeFn(null);
      return;
    }

    const files = await filesToB64(list);
    if (!this._multiple) {
      const [first] = files;
      this._onChangeFn(first);
      return;
    }
    this._onChangeFn(files);
  }

}
