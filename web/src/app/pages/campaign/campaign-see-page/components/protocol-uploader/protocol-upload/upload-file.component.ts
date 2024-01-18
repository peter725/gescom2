import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  HostListener,
  Input, OnChanges,
  Output, SimpleChanges,
  ViewChild
} from '@angular/core';
import { AbstractControl, FormGroup } from '@angular/forms';
import {SignFile} from "@libs/sdk/document";
import { ColumnSource, ColumnSrc } from '@base/shared/collections';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'tsw-file-upload',
  templateUrl: "./upload-file.component.html",
  styleUrls: ["./upload-file.component.scss"]
})
export class UploadFileComponent<T=any> {


  @ViewChild("fileUpload", {read: ElementRef})
  fileUpload!: ElementRef;
  readonly source = this.createDataSource();

  @Input() requiredFileType = '';
  @Input() form: FormGroup|any;
  @Output() upload = new EventEmitter<SignFile>();


  @HostListener('change', ['$event.target.files'])
  async handleChangeInput(list: FileList) {
    if (list.length > 0) {
      // this.form.patchValue({fileName: list.item(0)?.name || ''})
      this.form.patchValue({fileName: 'procesando' || ''})
      const file = list.item(0)
      if (file !== null)
        this.upload.emit({
          documentType: this.form.get("documentType")?.value,
          form: this.form,
          b64: "",
          file,
          name: ""
        });
    }
  }

  columns: ColumnSource | undefined;
  protected createDataSource() {
    return new MatTableDataSource<T>([]);
  }
  protected getColumns(): ColumnSrc[] {
    return [
      'name',
      'type',
      'size',
      'createdAt',
      'updatedAt',
    ];
  }


}


