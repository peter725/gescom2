import {
  Component, Input,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatTable, MatTableModule } from '@angular/material/table';
import { fileToB64 } from '@libs/file';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { TswSelectModule } from '@base/shared/select';
import { MatIconModule } from '@angular/material/icon';
import { DatePipe } from '@angular/common';
import { FileData } from '@libs/sdk/file-data';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { firstValueFrom } from 'rxjs';
import {CommonsModule} from "@base/shared/pages/commons.module";
import {CommonModule} from "@angular/common";
import { ActivatedRoute } from '@angular/router';

interface DocumentResponse {
  id: number;
  campaignId: number;
  createAt: string;
  documentType: {
    id: number;
    name: string;
    required: boolean;
    state: number;
  };
  name: string;
  extension: string;
  base64: string;
}

const ELEMENT_DATA: FileData[] = [];

@Component({
  selector: 'tsw-file-upload',
  templateUrl: "./upload-file.component.html",
  standalone: true,
  imports: [
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    TswSelectModule,
    MatTableModule,
    ReactiveFormsModule,
    MatIconModule,
    DatePipe,
    CommonsModule,
    CommonModule
  ]
})
export class UploadFileComponent <T=any> {

  @Input() documents: any;
  @Input() documentTypeId: any;
  @Input() canUpload: boolean = true;
  @Input() canDelete: boolean = true;
  displayedColumns: string[] = ['name'];
  dataSource = [...ELEMENT_DATA];
  form: FormGroup;
  selectedFile: File | undefined;
  @ViewChild(MatTable) table: MatTable<FileData> | undefined;

  documentsArray: { id: number, name: string }[] = [];

  idCampaign: any;

  constructor(
    private fb: FormBuilder,
    private crudService: CrudImplService,
    private route: ActivatedRoute) {

    this.form = this.fb.group({
      description: this.fb.control(null),
      typeDocument: this.fb.control(null),
      date: this.fb.control(null),
    });
  }

  ngOnInit(): void {
    this.idCampaign = this.route.snapshot.paramMap.get('id');
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['documents'].currentValue) this.loadDocuments();
  }

  //CARGAR DOCUMENTOS
  private async loadDocuments(): Promise<void> {

    this.documentsArray = [];
    const id = this.idCampaign;

    if (this.documents && this.documents.length > 0) {
      // Itera sobre los documentos y guarda el nombre y el ID de cada uno en el array
      this.documents.forEach((document: { id: number, name: string, state: number }) => {
        if (document && document.state === 1) {
          this.documentsArray.push({ id: document.id, name: document.name });
        }
      });

      // Ordena el array por el ID de más nuevo a más viejo
      this.documentsArray.sort((a, b) => b.id - a.id);

    } else {
      console.log('No documents found.');
    }
  }

  // AGREGAR DOCUMENTO
  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  getExtensionFromFileName(fileName: string): string {
    const parts = fileName.split('.');
    return parts[parts.length - 1];
  }

  async addFile() {
    if (this.selectedFile) {
      fileToB64(this.selectedFile).then(result => {
        // Obtener la extensión del archivo seleccionado
        const fileExtension = this.getExtensionFromFileName(this.selectedFile!.name);

        // Crear el objeto para enviar a la API
        let nuevoDocumento: any = {
          campaignId: this.idCampaign,
          createAt: new Date().toISOString(),
          documentType: {
            id: this.documentTypeId ? this.documentTypeId : 0,
            name: "Nombre del tipo de documento",
            state: 1
          },
          name: result.name,
          extension: fileExtension,
          base64: result.data,
          sign: ""
        };

        const config = {
          resourceName: 'document',
          pathParams: {},
        };

        // Enviar el nuevo documento a la API utilizando el servicio CRUD
        this.crudService.create(nuevoDocumento, config).subscribe(
          (response: any) => {
            location.reload();
            // this.loadDocuments();
          },
          (error: any) => {
            console.error("Error al agregar el documento:", error);
          }
        );

      }).catch(error => {
        console.error("Error al convertir archivo a base64:", error);
      });
    } else {
      console.log("No se seleccionó archivo.");
    }
  }

  //BORRADO DEL DOCUMENTO
  async deleteFile(idDocument: number) {
    try {
      await this.crudService.deleteId(idDocument, {
        resourceName: 'document',
        pathParams: { id: idDocument } // Pasamos el ID del documento aquí
      }).toPromise();
      location.reload();
      // this.loadDocuments();
    } catch (error) {
      console.error('Hubo un error al eliminar el documento:', error);
    }
  }

  // DESCARGA DEL DOCUMENTO
  async downloadFile(documentID: string | number) {
    const config: RequestConfig = {
      resourceName: 'documentList',
      pathParams: {
        id: documentID,
      },
    };
    const operation = this.crudService.findById(documentID as number, config);
    const res = await firstValueFrom(operation);

    if ('base64' in res && 'name' in res) {
      const documentData = res as unknown as DocumentResponse;
      this.downloadBase64File(documentData.base64, documentData.name, documentData.extension);

    } else {
      console.error('Error: Invalid response format');
    }
  }

  downloadBase64File(base64Data: string, fileName: string, extension: string) {
    const blob = this.base64ToBlob(base64Data, extension);
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  private base64ToBlob(base64Data: string, extension: string) {
    const byteString = atob(base64Data);
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }

    let mimeType = 'application/pdf'; // Default to PDF
    switch (extension.toLowerCase()) {
      case 'docx':
        mimeType = 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
        break;
      case 'xlsx':
        mimeType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
        break;
      case 'pdf':
        mimeType = 'application/pdf';
        break;
    }

    return new Blob([ab], { type: mimeType });

  }

}
