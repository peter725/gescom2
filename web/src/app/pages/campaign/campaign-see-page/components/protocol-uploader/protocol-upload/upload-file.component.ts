import {
  Component, Output,
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
import { CrudImplService } from '@libs/crud-api';

const ELEMENT_DATA: FileData[] = [];

@Component({
  selector: 'tsw-file-upload',
  templateUrl: "./upload-file.component.html",
  standalone: true,
  imports: [
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

  displayedColumns: string[] = ['date','size','type','description','openFile','delete'];
  dataSource = [...ELEMENT_DATA];
  form: FormGroup;
  selectedFile : File | undefined;
  @ViewChild(MatTable) table: MatTable<FileData> | undefined;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<UploadFileComponent>,
    private crudService: CrudImplService) {

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

  addFile(): void {
    if (this.selectedFile) {
      fileToB64(this.selectedFile).then(result => {
        // Obtener la extensión del archivo seleccionado
        const fileExtension = this.getExtensionFromFileName(this.selectedFile!.name);

        // Crear el registro incluyendo la extensión del archivo
        let nuevoRegistro: FileData = {
          ...this.form.value,
          b64: result.data, // Asumiendo que result.data contiene el contenido base64 del archivo
          id: this.contadorId++,
          /*campaignId: ,*/ // Asumiendo que esta variable contiene el ID de la campaña
          date: new Date().toISOString(),
          name: result.name, // Asumiendo que este es el nombre del archivo
          extension: fileExtension, // Almacenar la extensión del archivo
          size: this.formatBytesToKB(result.size), // Usamos la función de conversión aquíextension:  // Almacenar la extensión del archivo
        };

        this.dataSource.push(nuevoRegistro);
        console.log("DATASET=", this.dataSource);
        this.table?.renderRows();

        this.form.reset();
        console.log("Registro agregado:", nuevoRegistro);
      }).catch(error => {
        console.error("Error al convertir archivo a base64:", error);
      });
    } else {
      console.log("No se seleccionó archivo.");
    }
  }

  formatBytesToKB(bytes: number): string {
    return (bytes / 1024).toFixed(2) + ' KB';
  }

  getExtensionFromFileName(fileName: string): string {
    const parts = fileName.split('.');
    return parts[parts.length - 1];
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

  async saveFile() {
    const payload = {
      file: this.selectedFile,
      description: this.form.get('description')?.value,
      typeDocument: this.form.get('typeDocument')?.value,
      date: this.form.get('date')?.value
    };
    console.log("PAYLOAD=",payload);
    const request = this.crudService.create<void>(payload, {
      resourceName: 'document',
    });
    this.dialogRef.close(this.dataSource);
    await request;
  }
}


