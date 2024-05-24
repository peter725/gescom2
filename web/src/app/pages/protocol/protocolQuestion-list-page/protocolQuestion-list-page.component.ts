import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { ExportFileType } from '@base/shared/export-file';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';
import { ColumnSrc } from '@base/shared/collections';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { firstValueFrom } from 'rxjs/internal/firstValueFrom';
import { SharedDataService } from '@base/services/sharedDataService';


interface TableRow {
  index: number;
  codeQuestion: string;
  question: string;
  si: boolean;
  no: boolean;
  noProcede: boolean;
  [key: string]: number | string | boolean; // Índice de tipo para aceptar cualquier propiedad adicional
}

@Component({
  selector: 'tsw-protocolQuestion-list-page',
  templateUrl: './protocolQuestion-list-page.component.html',
  styleUrls: ['./protocolQuestion-list-page.component.scss'],
})



export class ProtocolQuestionListPageComponent extends BaseListPageComponent<any> implements OnInit {

  @Output() selectionChanged: EventEmitter<{row: any, selected: boolean}> = new EventEmitter();
  @Output() respuestasUsuarioChanged: EventEmitter<any[]> = new EventEmitter<any[]>();

  readonly resourceName = '';


  dataSourceSample: any[] = []; // Aquí almacenaremos los datos de ejemplo
  dataSourceQuestion: any[] = []; // Aquí almacenaremos los datos de ejemplo
  idProtocol: any;
  codeProtocol: any;
  questionsArray: any;

  respuestasUsuario: any[] = [];


  // override exportFormats = [ExportFileType.CSV];
  override downloadFileName = 'pages.infringement.title';

  constructor(
    crudService: CrudImplService<any>,
    filterService: FilterService,
    private sharedDataService: SharedDataService
    //private sampleCtx: AppContextService,
  ) {
    super(crudService, filterService);
  }

  override async ngOnInit() {
    // this.loadExampleData();
    this.sharedDataService.sharedData$.subscribe(data => {
      this.idProtocol = data.id;
      this.codeProtocol = data.code;
    });
    
    this.fetchQuestions();
    //this.monitorCtxChanges();
  }

  checkboxChanged(event: MatCheckboxChange, row: any, column: string): void {
    // if (event.checked) {
    //   // Si el checkbox actual está siendo marcado, desmarca los otros checkboxes de la misma fila
    //   if (column === 'si') {
    //     row.no = false;
    //     row.noProcede = false;
    //   } else if (column === 'no') {
    //     row.si = false;
    //     row.noProcede = false;
    //   } else if (column === 'noProcede') {
    //     row.si = false;
    //     row.no = false;
    //   }
    // }
  }

 
  private async fetchQuestions(): Promise<void> {
    const id = this.idProtocol;
    const code = this.codeProtocol.toString();

    console.log('id recuperado: ' + id + 'code recuperado: ' + code)
    this.dataSourceQuestion = await firstValueFrom(this.crudService.findAny( {
      resourceName: 'protocolQuestionsList',
      queryParams: {
        id: id,
        code: code
      },
    }));

    console.log(this.dataSourceQuestion);
    this.questionsArray = this.dataSourceQuestion;
  
  }


  override select(ev: MatCheckboxChange, row: any): void {
    if (ev) {
      this.selection.toggle(row);
      // Emite el evento con el objeto de la fila y el estado de selección
      this.selectionChanged.emit({row: row, selected: this.selection.isSelected(row)});
    }
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    //const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;

    config.queryParams = {
      ...config.queryParams
      //scope,
    };
    return config;
  }
  

  public guardarRespuestas() {
    // Inicializa la lista de respuestas
    this.respuestasUsuario = [];
  
    // Itera sobre cada fila en questionsArray
    this.questionsArray.forEach((element: TableRow, index: number) => {
      // Inicializa la respuesta para esta fila
      let respuestasFila = '';
  
      // Verifica si se ha seleccionado cada opción de la fila y construye la respuesta
      if (element.si) {
        respuestasFila += 'S' + (element.codeQuestion || '') + '+';
      }
      if (element.no) {
        respuestasFila += 'N' + (element.codeQuestion || '') + '+';
      }
      if (element.noProcede) {
        respuestasFila += 'NP' + (element.codeQuestion || '') + '+';
      }
  
      // Elimina el último '+' si hay alguna respuesta
      if (respuestasFila) {
        respuestasFila = respuestasFila.slice(0, -1);
      }
  
      // Verifica si al menos una opción de la fila ha sido seleccionada
      if (respuestasFila) {
        // Agrega la respuesta al arreglo de respuestas
        this.respuestasUsuario.push({
          index: index + 1,
          pregunta: element.codeQuestion || '', // Usa una cadena vacía si codeQuestion es undefined
          respuesta: respuestasFila
        });
      }
    });
  
    // Verifica los checkboxes generales y agrega las respuestas correspondientes
    const checkboxValues = [
      { id: 'DC1', pregunta: 'Nº de establecimientos existentes' },
      { id: 'DC8', pregunta: 'Nº de establecimientos controlados' },
      { id: 'DC9', pregunta: 'Controlados' },
      { id: 'DC10', pregunta: 'Correctos' },
      { id: 'DC11', pregunta: 'Incorrectos' }
    ];
  
    const checkboxRespuestas: string[] = [];
    checkboxValues.forEach(checkbox => {
      const checkboxElement = document.getElementById(checkbox.id) as HTMLInputElement;
      if (checkboxElement.checked) {
        checkboxRespuestas.push(checkbox.id);
      }
    });
  
    // Agrega las respuestas de los checkboxes generales seleccionados a la lista respuestasUsuario
    if (checkboxRespuestas.length > 0) {
      const checkboxRespuestasStr = checkboxRespuestas.join(' + ');
      this.respuestasUsuario.push({
        index: this.respuestasUsuario.length + 1,
        respuesta: checkboxRespuestasStr,
      });
    }
  
    console.log(this.respuestasUsuario);
    this.sharedDataService.updateSharedData(this.respuestasUsuario);
  }

  // Método para seleccionar todos los elementos de una columna específica en la tabla
selectAllChecks(column: string): void {
  this.questionsArray.forEach((element: TableRow) => {
    element[column] = true;
  });
}

// Método para verificar si todos los elementos de una columna específica están seleccionados
allSelected(column: string): boolean {
  if(this.questionsArray){
    return this.questionsArray.every((element: TableRow) => element[column]);
  }else{
    return false;
  }
  
}

  protected getColumns(): ColumnSrc[] {
    return [
      'index',
      'code',
      'question',
      'si',
      'no',
      'noProcede'
    ];
  }

}