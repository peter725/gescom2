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
import { fileToB64 } from '@libs/file';

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
  @Input() formUpload: FormGroup|any;
  @Output() upload = new EventEmitter<SignFile>();




  @HostListener('change', ['$event.target.files'])
  async handleChangeInput(list: FileList) {
    console.log(this.requiredFileType);
    console.log('aqui se imprime el formulario',this.formUpload);


    if (list.length > 0) {
      this.formUpload.patchValue({fileName: 'procesando' || ''});
      const file = list.item(0);
      const b64 = await fileToB64(file);
      console.log(file);
      console.log(this.formUpload.get("documentType")?.value);
      if (file !== null)
        this.upload.emit({
          sign: "",
          documentType: file,
          form: this.formUpload,
          b64: b64.data,
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


