import {
  Component,
  ViewChild
} from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatTable, MatTableModule } from '@angular/material/table';
import { fileToB64 } from '@libs/file';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { TswSelectModule } from '@base/shared/select';
import { MatIconModule } from '@angular/material/icon';
import { DatePipe } from '@angular/common';
import { FileData } from '@libs/sdk/file-data';

const ELEMENT_DATA: FileData[] = [];

@Component({
  selector: 'tsw-file-upload',
  templateUrl: "./upload-file.component.html",
  standalone: true,imports: [
    MatDialogModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    TswSelectModule,
    MatTableModule,
    ReactiveFormsModule,
    MatIconModule,
    DatePipe
  ]
})
export class UploadFileComponent<T=any> {

  private url = '';

  private contadorId = 1;

  displayedColumns: string[] = ['date','out','type','description','openFile','delete'];
  dataSource = [...ELEMENT_DATA];
  form: FormGroup;
  selectedFile : File | undefined;
  @ViewChild(MatTable) table: MatTable<FileData> | undefined;

  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<UploadFileComponent>) {

    this.form = this.fb.group({
      description : this.fb.control(null),
      typeDocument : this.fb.control(null),
      date : this.fb.control(null),
    });

  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    console.log("Archivo="+this.selectedFile?.name)
  }

  addFile() : void {

    let nuevoRegistro: FileData = this.form.value;

    if (this.selectedFile) {

      fileToB64(this.selectedFile).then(result=>{
        nuevoRegistro.b64 = result;
      });
      /*
      this.convertirABase64( this.selectedFile).then(base64=>{
          nuevoRegistro.base64=base64;
      });
       */
    }

    const pipe = new DatePipe('en-US');
    nuevoRegistro.id =  this.contadorId++;
    this.dataSource.push(nuevoRegistro);
    console.log("DATASET="+this.dataSource)
    this.table?.renderRows();

    this.form?.reset();
  }

  deleteFile(id : number) : void {
    console.log("registro a eliminar:"+id);
    this.dataSource = this.dataSource.filter(register=>register.id !== id);
    this.table?.renderRows();
    this.form?.reset();
  }

  downloadFile(id : number) {

    const register = this.dataSource.find(item=>item.id==id);
    const reader = new FileReader();

    if(register?.b64.data!=undefined){
      const file = this.base64AFile(register?.b64.data, register?.b64.name);

      if(file!=undefined) {
        const url = URL.createObjectURL(file);
        const a = document.createElement('a');
        a.href = url;
        a.download = file.name; // Esto le dará al archivo el mismo nombre que tenía originalmente
        document.body.appendChild(a);
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      }

    }
  }

  closeDialog(): void {
    this.dialogRef.close(this.dataSource);
  }

  base64AFile(base64: string, name : string) : File {

    // Elimina el prefijo de la cadena Base64 si existe
    const base64SinPrefijo = base64.split(';base64,').pop();

    // Convierte la cadena Base64 a un array de bytes
    const contenido = base64SinPrefijo ? atob(base64SinPrefijo) : '';
    const arrayBytes = new Uint8Array(contenido.length);

    for (let i = 0; i < contenido.length; i++) {
      arrayBytes[i] = contenido.charCodeAt(i);
    }

    // Crea el blob a partir del array de bytes
    const blob = new Blob([arrayBytes], { type: 'tipo/mime' }); // Reemplaza 'tipo/mime' con el tipo MIME real del archivo

    // Crea y retorna el objeto File
    return new File([blob], name, { type: 'tipo/mime' });

  }


  /*@ViewChild("fileUpload", {read: ElementRef})
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
  }*/


}


