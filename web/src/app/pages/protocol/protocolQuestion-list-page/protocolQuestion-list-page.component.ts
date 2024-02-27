import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { ExportFileType } from '@base/shared/export-file';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { FilterService } from '@base/shared/filter';
import { ColumnSrc } from '@base/shared/collections';
import { Infringement } from '@libs/sdk/infringement';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { firstValueFrom } from 'rxjs/internal/firstValueFrom';
import { SharedDataService } from '@base/services/sharedDataService';


// interface questionResponse {
//   question: string;
//   si: boolean;
//   no: boolean;
//   noProcede: boolean;
// }


interface TableRow {
  index: number;
  codeQuestion: string;
  question: string;
  si: boolean;
  no: boolean;
  noProcede: boolean;
  // Agrega aquí otras propiedades si las tienes
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
  
  //   // Verifica si protocolData tiene la propiedad 'questions' antes de acceder a ella
  // if ('questions' in this.dataSourceQuestion) {
  //   this.questionsArray = this.dataSourceQuestion.questions;
  //   console.log('array question: ' + JSON.stringify(this.questionsArray))
  // } else {
  //   console.error('El objeto no tiene la propiedad "questions"');
  // }
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
  
  //para guardar las respuestas
    public guardarRespuestas() {
      this.respuestasUsuario = this.questionsArray.map((element: TableRow, index: number) => {
        let respuesta = '';
        if (element.si) {
          respuesta = 'S';
        } else if (element.no) {
          respuesta = 'N';
        } else if (element.noProcede) {
          respuesta = 'NP';
        }
        return {
          index: index + 1,
          pregunta: element.codeQuestion,
          respuesta: respuesta
        };
      });
    
      console.log(this.respuestasUsuario);
      this.sharedDataService.updateSharedData(this.respuestasUsuario);

      // this.respuestasUsuarioChanged.emit(this.respuestasUsuario);
      
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

  //private monitorCtxChanges() {
  // this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  //}
}