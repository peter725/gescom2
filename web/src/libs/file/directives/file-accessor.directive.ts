import { Directive } from '@angular/core';
import { BaseFileInputAccessorDirective } from './base-file-input-accessor.directive';

/**
 * Links a file input raw value with its respective NgControl value.
 * Expected control value is FileList.
 */
@Directive({
  selector: 'input[appFileAccessor][type=file]',
})
export class FileAccessorDirective extends BaseFileInputAccessorDirective<FileList> {

  protected handleInput(list: FileList) {
    if (!list.length) {
      this._onChangeFn(null);
      return;
    }

    this._onChangeFn(list);
  }

}
