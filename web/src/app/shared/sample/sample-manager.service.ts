import { Injectable } from '@angular/core';
import { CrudImplService, PageReqBuilder, RequestConfig, SortDirection } from '@tulsa/libs/crud-api';
import { ModelStates } from '@tulsa/libs/sdk/common';
import { EvaluableBusinessRule } from '@tulsa/libs/sdk/evaluable-business-rules';
import { FieldElementType, VTulsaFieldModule } from '@tulsa/libs/sdk/field-module';
import { FieldModuleAssociation } from '@tulsa/libs/sdk/field-module-association';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { TulsaLanguage } from '@tulsa/libs/sdk/language';
import { TulsaModule } from '@tulsa/libs/sdk/module';
import {
  TulsaSample,
  TulsaSampleData,
  TulsaSampleResult,
  TulsaSampleState,
  TulsaSampleStateCode
} from '@tulsa/libs/sdk/sample';
import { TulsaSampleTemplate } from '@tulsa/libs/sdk/sample-template';
import { BehaviorSubject, combineLatest, firstValueFrom, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { OngoingSampleForm } from './ongoing-sample-form';
import { SampleContextService } from './sample-context.service';
import { LockedSampleForm, SampleForm } from './sample-form';


@Injectable({ providedIn: 'root' })
export class SampleManagerService {

  private _helpIndicator = new BehaviorSubject(false);

  private sampleForm: SampleForm | undefined;

  constructor(
    private crudService: CrudImplService,
    private sampleCtx: SampleContextService,
  ) {
  }

  async save(sample: Partial<TulsaSample>) {
    const config: RequestConfig = {
      resourceName: 'samples',
    };

    let request: Observable<TulsaSample>;
    const sampleState = !sample.sampleState
      ? (await firstValueFrom(this.getInitialSampleState()))[0]
      : sample.sampleState;
    sample.sampleState = { id: sampleState.id } as TulsaSampleState;

    const payload = { ...sample } as TulsaSample;
    if (payload.id) {
      config.pathParams = { id: payload.id };
      request = this.crudService.update<TulsaSample>(payload.id, payload, config);
    } else {
      request = this.crudService.create<TulsaSample>(payload, config);
    }

    return firstValueFrom(request);
  }

  async getSampleForm(config: { sampleId: number, template?: TulsaSampleTemplate }): Promise<SampleForm> {
    const [module, scope, season, language] = await firstValueFrom(combineLatest([
      this.sampleCtx.module$,
      this.sampleCtx.scope$,
      this.sampleCtx.season$,
      this.sampleCtx.language$,
    ]));

    const sampleId = config.sampleId;
    const isNew = !sampleId;
    if (isNew) {
      const fields = await firstValueFrom(this.getFieldsModules(module));
      const associations = await this.getAssociationRules(fields);
      const comboValues = await firstValueFrom(this.getComboValues(module, language, fields));
      const businessRules = await firstValueFrom(this.getBusinessRules(module));
      const [sampleState] = await firstValueFrom(this.getInitialSampleState());

      let data: TulsaSampleData[] = [];
      let results: TulsaSampleResult[] = [];
      if (config.template?.sampleId) {
        const template = await firstValueFrom(this.getSample(config.template.sampleId, module.id, scope.scopeCode, season.id));
        data = template.data.map(item => ({
          ...item,
          id: 0,
          sampleId: 0,
        }));
        results = template.results.map(result => ({
          ...result,
          id: 0,
          sampleId: 0,
          data: result.data.map(v => ({ ...v, resultId: 0, id: 0 })),
        }));
      }

      const sampleSrc: TulsaSample = {
        id: 0,
        season,
        module,
        scopeCode: scope.scopeCode,
        pubCode: '',
        state: ModelStates.ON,
        sampleState,
        data,
        results,
      };
      this.sampleForm = new OngoingSampleForm(fields, language, sampleSrc, associations, businessRules, comboValues);
    } else {
      const sample = await firstValueFrom(this.getSample(sampleId, module.id, scope.scopeCode, season.id));
      // Modificar esta aberración por una implementación basada en un valor de api
      const isOngoing = true;
      if (isOngoing) {
        const fields = await firstValueFrom(this.getFieldsModules(module));
        const associations = await this.getAssociationRules(fields);
        const comboValues = await firstValueFrom(this.getComboValues(module, language, fields));
        const businessRules = await firstValueFrom(this.getBusinessRules(module));

        this.sampleForm = new OngoingSampleForm(fields, language, sample, associations, businessRules, comboValues);
      } else {
        // carga de los campos brutos por código, en esta situación no nos podemos basar en campos-módulos
        // porque podrían estar deshabilitados
        // las reglas de validación y assoc no son necesarias
        this.sampleForm = new LockedSampleForm(sample);
      }
    }
    return this.sampleForm;
  }

  getCurrentSampleForm(): SampleForm {
    if (!this.sampleForm) {
      throw new Error('Sample form accessed before initialization');
    }
    return this.sampleForm;
  }

  get helpIndicator$() {
    return this._helpIndicator.asObservable();
  }

  get helpIndicator() {
    return this._helpIndicator.value;
  }

  set helpIndicator(value: boolean) {
    if (this._helpIndicator.value !== value) {
      this._helpIndicator.next(value);
    }
  }

  /**
   * Loads the sample based on the current configured module, scope and season.
   */
  private getSample(sampleId: number, moduleId: number, scopeCode: string, seasonId: number) {
    const config: RequestConfig = {
      resourceName: 'samples',
      queryParams: {
        moduleId,
        scopeCode,
        seasonId,
      },
      pathParams: {
        id: sampleId,
      },
    };
    return this.crudService.findById<TulsaSample>(sampleId, config);
  }

  private getFieldsModules(module: TulsaModule) {
    const config: RequestConfig = {
      resourceName: 'sampleFormFields',
      pathParams: {
        module: module.id,
      },
      pageReq: PageReqBuilder.unpaged([
        ['idPosition', SortDirection.ASC],
        ['positionRow', SortDirection.ASC],
        ['positionColumn', SortDirection.ASC],
      ])
    };
    return this.crudService.findAll<VTulsaFieldModule>(config).pipe(
      map(page => page.content),
    );
  }

  private getAssociationRules(fields: VTulsaFieldModule[]) {
    const moduleIdList = new Set(fields.map(v => v.id));
    const config: RequestConfig = {
      resourceName: 'fieldModuleAssociations',
      queryParams: {
        parentFieldModuleId: Array.from(moduleIdList)
      },
      pageReq: PageReqBuilder.unpaged([
        ['parentFieldModuleId', SortDirection.ASC],
        ['fieldModuleId', SortDirection.ASC],
      ]),
    };
    return firstValueFrom(this.crudService.findAll<FieldModuleAssociation>(config).pipe(
      map(page => page.content),
    ));
  }

  private getComboValues(module: TulsaModule, language: TulsaLanguage, fields: VTulsaFieldModule[]) {
    const elementNames = new Set(fields.filter(value => value.elementType === FieldElementType.COMBO).map(value => value.elementName));
    const config: RequestConfig = {
      resourceName: 'sampleFormValues',
      queryParams: {
        codeIso: language.isoCode,
        elementName: Array.from(elementNames),
      },
      pathParams: {
        module: module.id,
      },
      pageReq: PageReqBuilder.unpaged([
        ['elementName', SortDirection.ASC],
        ['orden', SortDirection.ASC],
      ]),
    };
    return this.crudService.findAll<TulsaFieldModuleTerm>(config).pipe(
      map(page => page.content),
    );
  }

  private getInitialSampleState() {
    const config: RequestConfig = {
      resourceName: 'sampleStates',
      queryParams: {
        code: TulsaSampleStateCode.E01,
      },
      pageReq: PageReqBuilder.unpaged([
        ['id', SortDirection.ASC],
      ]),
    };
    return this.crudService.findAll<TulsaSampleState>(config).pipe(
      map(page => page.content),
    );
  }

  private getBusinessRules(module: TulsaModule) {
    const config: RequestConfig = {
      resourceName: 'businessRuleDefinitions',
      queryParams: { moduleId: module.id },
      pageReq: PageReqBuilder.unpaged([])
    };
    return this.crudService.findAll<EvaluableBusinessRule>(config).pipe(
      map(page => page.content)
    );
  }
}
