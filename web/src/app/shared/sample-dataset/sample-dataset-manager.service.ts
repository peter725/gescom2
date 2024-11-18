import { Injectable } from '@angular/core';
import {
  AbstractControl,
  AsyncValidatorFn,
  FormArray,
  FormControl,
  FormGroup,
  ValidationErrors,
  Validators
} from '@angular/forms';
import { AppContextService } from '@base/shared/app-context';
import {
  BusinessRuleEvaluator,
  BusinessRuleExpressionCtx,
  BusinessRuleFactory
} from '@base/shared/sample-business-rule';
import { CrudImplService, PageReqBuilder, SortDirection } from '@libs/crud-api';
import { EvaluableBusinessRule } from '@libs/sdk/evaluable-business-rules';
import {
  DefaultValuesCodes,
  FieldElementType,
  FieldPositionType,
  VFieldModule
} from '@libs/sdk/field-module';
import { VFieldModuleAssignment } from '@libs/sdk/field-module-assignment';
import { VFieldModuleAssociation } from '@libs/sdk/field-module-association';
import { FieldModuleTerm } from '@libs/sdk/field-module-term';
import { PositionField } from '@libs/sdk/position-field';
import { Sample, SampleSave } from '@libs/sdk/sample';
import {
  SampleDataset,
  SampleDatasetCtx,
  SampleDatasetResultValue,
  SampleDatasetSave
} from '@libs/sdk/sample-dataset';
import { SampleTemplate, SampleTemplateSave } from '@libs/sdk/sample-template';
import { SimpleUidGenerator } from '@libs/utils/uid';
import { BehaviorSubject, debounceTime, distinctUntilChanged, firstValueFrom, Subscription } from 'rxjs';
import { DatasetColumnUI, DatasetCoordinates, DatasetGroupContainerUI, DatasetRowContainerUI } from './sample-dataset';
import {
  BusinessRulesGroups,
  DatasetContainerType,
  DatasetForm,
  DatasetFormGroup,
  DatasetFormValue,
  DatasetLoadOptions,
  DatasetLoadType,
  DEFAULT_VALUE_SEPARATOR,
  SampleConfig,
  SampleProgrammingConfig,
  SampleTemplateConfig,
} from './sample-dataset.model';


@Injectable({ providedIn: 'root' })
export class SampleDatasetManagerService {
  private _loadType: DatasetLoadType = 'sample';
  private _createMode = new BehaviorSubject(true);
  private _helpIndicator = new BehaviorSubject(false);

  private _positions: Record<string, PositionField> = {};
  private _fieldsByName: Record<string, VFieldModule> = {};
  private _fieldsByPosition: Record<string, string[]> = {};

  private _comboValues: Record<string, FieldModuleTerm[]> = {};
  private _businessRuleByGroup: BusinessRulesGroups = { single: {}, common: [], result: [], global: [] };
  private _assignmentsByElement: Record<string, VFieldModuleAssignment[]> = {};
  private _associationsByElement: Record<string, Record<string, VFieldModuleAssociation[]>> = {};

  private _activeSubscriptions = new Subscription();

  // Fuente de datos cargada
  srcDataset: SampleDataset | undefined;
  // Formulario de recogida de datos
  datasetForm!: FormGroup<DatasetForm>;
  // Contenedor de la interfaz gráfica
  datasetContainer = {
    common: new BehaviorSubject<DatasetGroupContainerUI[]>([]),
    result: new BehaviorSubject<DatasetGroupContainerUI[]>([]),
  };

  constructor(
    private ctx: AppContextService,
    private crudService: CrudImplService,
  ) {
  }

  get showHelpIndicator$() {
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

  async load(config: DatasetLoadOptions) {
    // some cleanup required
    this._activeSubscriptions.unsubscribe();
    this._activeSubscriptions = new Subscription();
    // clean previous assoc rules

    this._loadType = config.type;
    await this.loadFields();
    await this.loadComboValues();
    await this.loadBusinessRules();
    await this.loadAssignmentRules();
    await this.loadAssociationRules();

    await this.loadDataset(config);

    await this.buildDatasets();
    await this.configureBusinessRules();
    // await this.monitorFormEvents();
    // complete process ?
  }

  async saveSample(config: SampleConfig) {
    const dataset = await this.getDataset();

    const src = this.srcDataset as Sample;
    const payload: SampleSave = {
      id: src?.sampleId ?? null,
      sampleId: src?.sampleId ?? null,
      templateSrcId: config.templateSrcId,
      programSrcId: config.programSrcId,
      ...dataset,
    };

    const requestConfig = {
      resourceName: 'samples'
    };
    const request = !payload.sampleId
      ? this.crudService.create<Sample>(payload, requestConfig)
      : this.crudService.update<Sample>(payload.sampleId, payload, {
        ...requestConfig,
        pathParams: { id: payload.sampleId }
      });

    const result = await firstValueFrom(request);
    this.srcDataset = undefined;
    return result;
  }

  async saveTemplate(config: SampleTemplateConfig) {
    const dataset = await this.getDataset();

    const src = this.srcDataset as SampleTemplate;
    const payload: SampleTemplateSave = {
      id: src?.templateId ?? null,
      templateId: src?.templateId ?? null,
      name: config.templateName,
      sharedAccess: config.sharedAccess,
      ...dataset,
    };

    const requestConfig = {
      resourceName: 'sampleTemplate',
    };
    const request = !payload.templateId
      ? this.crudService.create<SampleTemplate>(payload, requestConfig)
      : this.crudService.update<SampleTemplate>(payload.templateId, payload, {
        ...requestConfig,
        pathParams: { id: payload.templateId },
      });

    const result = await firstValueFrom(request);
    this.srcDataset = undefined;
    return result;
  }

  async saveProgramming(config: SampleProgrammingConfig) {
  }

  cleanup() {
    this._activeSubscriptions.unsubscribe();
    this._activeSubscriptions = new Subscription();

    this.srcDataset = undefined;
    this.datasetForm = undefined as any;
    this.datasetContainer.result.next([]);
    this.datasetContainer.common.next([]);
  }

  async getDataset(): Promise<SampleDatasetSave> {
    const dataset: SampleDatasetSave = {
      moduleId: (await firstValueFrom(this.ctx.module$)).id,
      seasonId: (await firstValueFrom(this.ctx.season$)).id,
      scopeCode: (await firstValueFrom(this.ctx.scope$)).scopeCode,

      common: {
        datasetId: null
      },
      results: [],
    };
    const raw = this.datasetForm.getRawValue();

    dataset.common.datasetId = raw.common.datasetId;
    Object.entries(raw.common).forEach(([ctrlName, rawValue]) => {
      const elementName = DatasetColumnUI.destructCtrlName(ctrlName);
      const field = this._fieldsByName[elementName];
      if (!field) {
        console.error(`Cannot find field definition for ${ ctrlName }`);
        return;
      }
      const value = this.compactValue({
        field,
        value: rawValue,
      });

      if (value != null) {
        dataset.common[elementName] = value;
      }
    });

    raw.result.forEach(group => {
      const result: SampleDatasetResultValue = {
        datasetId: group.datasetId,
        resultId: group.resultId,
      };
      Object.entries(group).forEach(([ctrlName, rawValue]) => {
        const elementName = DatasetColumnUI.destructCtrlName(ctrlName);
        const field = this._fieldsByName[elementName];
        if (!field) {
          console.error(`Cannot find field definition for ${ ctrlName }`);
          return;
        }

        const value = this.compactValue({
          field,
          value: rawValue,
        });

        if (value != null) {
          result[elementName] = value;
        }
      });
      dataset.results.push(result);
    });

    return dataset;
  }

  async addResultDataset() {
    const position = this._positions[FieldPositionType.RESULTADOS];
    const containerType = DatasetContainerType.RESULT;
    const nextIdx = this.datasetContainer.result.value.length;
    const groupUID = SimpleUidGenerator.createUid('G');
    const groupPosition = DatasetCoordinates.construct(containerType, [groupUID]);

    // obtener los datos del SRC
    // o de la plantilla
    // o de la programación
    // o de los datos preconfigurados para una nueva muestra
    const { rows, controls } = await this.buildDatasetContents({
      groupPosition,
      positionType: position.type,
    });

    const formGroup = new FormGroup<DatasetFormGroup>({
      datasetId: new FormControl(null),
      resultId: new FormControl(null),
      ...controls,
    });

    // Configuración business rules (ver si es posible abstraer)
    Object.entries(this._businessRuleByGroup.single).forEach(([elementName, evaluators]) => {
      const field = this._fieldsByName[elementName];
      if (!field || !field.positionIsRepeatable) return;

      const validators: AsyncValidatorFn[] = evaluators.map(e => this.compileSingleValidator(e));
      const ctrlName = DatasetColumnUI.constructCtrlName(elementName);
      const ctrl = formGroup.get(ctrlName);
      if (ctrl) ctrl.setAsyncValidators(validators);
    });

    const resultValidators: AsyncValidatorFn[] = this._businessRuleByGroup.result.map(e => this.compileGroupValidator(e));
    formGroup.setAsyncValidators(resultValidators);

    const uiGroup = new DatasetGroupContainerUI({
      formGroup,
      containerType: containerType,
      items: Object.values(rows),
      order: nextIdx,
      index: nextIdx,
      label: position.name,
      position: groupPosition,
    });

    this.datasetForm.controls.result.push(formGroup);
    const copy = [...this.datasetContainer.result.value];
    copy.push(uiGroup);
    this.datasetContainer.result.next(copy);
  }

  async removeResultDataset(positions: string | string[]) {
    const removePositions = Array.isArray(positions) ? positions : [positions];

    const resultDataset = [...this.datasetContainer.result.value];
    removePositions.forEach(position => {
      const idx = resultDataset.findIndex(v => v.position === position);
      const dataset = resultDataset[idx];
      const formGroup = this.datasetForm.controls.result.at(idx);

      if (dataset && formGroup && dataset.formGroup === formGroup) {
        resultDataset.splice(idx, 1);
        this.datasetContainer.result.next(resultDataset);
        this.datasetForm.controls.result.removeAt(idx);
      }
    });
  }

  async summarizeValidationErrors() {
    console.info(this.getFormErrors(this.datasetForm.controls.common));
    console.info(this.getFormErrors(this.datasetForm.controls.result));
  }

  private async loadDataset(config: DatasetLoadOptions) {
    switch (this._loadType) {
      case 'sample':
        await this.loadSample(config);
        break;
      case 'template':
        await this.loadTemplate(config);
        break;
      case 'programming':
        await this.loadProgramming(config);
        break;
    }
  }

  private async loadSample(config: DatasetLoadOptions) {
    // puede ser nueva o existente, la carga cambia según sea el caso

    const module = await firstValueFrom(this.ctx.module$);
    const season = await firstValueFrom(this.ctx.season$);
    const scope = await firstValueFrom(this.ctx.scope$);

    if (!config.id) return;

    // ver como cargar una plantilla o programación

    this.srcDataset = await firstValueFrom(this.crudService.findById<Sample>(config.id, {
      resourceName: 'samples',
      queryParams: {
        moduleId: module.id,
        scopeCode: scope.scopeCode,
        seasonId: season.id,
      },
      pathParams: {
        id: config.id,
      },
    }));
  }

  private async loadTemplate(config: DatasetLoadOptions) {
    // puede ser nueva o existente, la carga cambia según sea el caso
    if (!config.id) return;

    this.srcDataset = await firstValueFrom(this.crudService.findById<SampleTemplate>(config.id, {
      resourceName: 'sampleTemplate',
      queryParams: {},
      pathParams: {
        id: config.id,
      },
    }));
  }

  private async loadProgramming(config: DatasetLoadOptions) {
  }

  private async loadFields() {
    const module = await firstValueFrom(this.ctx.module$);
    const page = await firstValueFrom(this.crudService.findAll<VFieldModule>({
      resourceName: 'sampleFormFields',
      pathParams: {
        module: module.id,
      },
      pageReq: PageReqBuilder.unpaged([
        ['idPosition', SortDirection.ASC],
        ['positionRow', SortDirection.ASC],
        ['positionColumn', SortDirection.ASC],
      ]),
    }));

    this._positions = {};
    this._fieldsByName = {};
    this._fieldsByPosition = {};

    page.content.forEach(item => {
      if (!this._positions[item.positionType]) {
        this._positions[item.positionType] = {
          id: item.idPosition,
          languageId: item.languageId,
          name: item.positionName,
          type: item.positionType,
          state: item.stateId,
          positionIsRepeatable: item.positionIsRepeatable,
        };
      }

      if (!this._fieldsByName[item.elementName]) {
        this._fieldsByName[item.elementName] = item;
      }

      if (!this._fieldsByPosition[item.positionType]) {
        this._fieldsByPosition[item.positionType] = [];
      }
      this._fieldsByPosition[item.positionType].push(item.elementName);
    });

  }

  private async loadBusinessRules() {
    const module = await firstValueFrom(this.ctx.module$);
    const page = await firstValueFrom(this.crudService.findAll<EvaluableBusinessRule>({
      resourceName: 'businessRuleDefinitions',
      queryParams: {
        moduleId: module.id,
        segment1: true, // cargar solamente el tramo 1
      },
      pageReq: PageReqBuilder.unpaged([])
    }));

    const groups: BusinessRulesGroups = {
      single: {},
      common: [],
      result: [],
      global: [],
    };
    page.content.forEach(rule => {
      // Omitir las reglas incorrectas
      if (rule.verification == null || !rule.checkedElements.length) return;

      const compiledRule = BusinessRuleFactory.compileRule(rule);
      const applyOnEach = rule.forEachElements?.length > 0;
      if (applyOnEach) {
        groups.global.push(compiledRule);
        return;
      }

      const numOfCheckedElements = rule.checkedElements?.length || 0;
      if (numOfCheckedElements === 1) {
        const [elementName] = rule.checkedElements;
        if (!groups.single[elementName]) groups.single[elementName] = [];
        groups.single[elementName].push(compiledRule);
        return;
      }

      const sorted = rule.checkedElements
        .reduce<{ common: string[], result: string[] }>((acc, elementName) => {
          const field = this._fieldsByName[elementName];
          if (!field) return acc;

          const list = field.positionIsRepeatable ? acc.result : acc.common;
          list.push(elementName);
          return acc;
        }, { common: [], result: [] });

      const hasCommon = sorted.common.length > 0;
      const hasResults = sorted.result.length > 0;
      if (hasCommon && hasResults) {
        groups.global.push(compiledRule);
      } else if (hasCommon && !hasResults) {
        groups.common.push(compiledRule);
      } else if (hasResults) {
        groups.result.push(compiledRule);
      }
    });

    this._businessRuleByGroup = groups;
  }

  private async loadAssignmentRules() {
    const module = await firstValueFrom(this.ctx.module$);
    const page = await firstValueFrom(this.crudService.findAll<VFieldModuleAssignment>({
      resourceName: 'fieldModuleAssignment',
      pageReq: PageReqBuilder.unpaged(),
      queryParams: {
        moduleId: module.id
      },
    }));

    this._assignmentsByElement = page.content.reduce<Record<string, VFieldModuleAssignment[]>>((acc, value) => {
      if (!acc[value.sourceElementName]) acc[value.sourceElementName] = [];
      acc[value.sourceElementName].push(value);
      return acc;
    }, {});
  }

  private async loadAssociationRules() {
    const module = await firstValueFrom(this.ctx.module$);
    const page = await firstValueFrom(this.crudService.findAll<VFieldModuleAssociation>({
      resourceName: 'fieldModuleAssociations',
      pageReq: PageReqBuilder.unpaged(),
      queryParams: {
        moduleId: module.id
      },
    }));

    this._associationsByElement = page.content.reduce<Record<string, Record<string, VFieldModuleAssociation[]>>>((acc, value) => {
      if (!acc[value.sourceElementName]) acc[value.sourceElementName] = {};
      if (!acc[value.sourceElementName][value.targetElementName]) acc[value.sourceElementName][value.targetElementName] = [];

      acc[value.sourceElementName][value.targetElementName].push(value);
      return acc;
    }, {});
  }

  private async loadComboValues() {
    const language = await firstValueFrom(this.ctx.language$);
    const module = await firstValueFrom(this.ctx.module$);
    const fields = Object.values(this._fieldsByName)
      .filter(({ elementType }) => elementType === FieldElementType.COMBO)
      .map(value => value.elementName);
    const uniqueFields = Array.from(new Set(fields));

    const page = await firstValueFrom(this.crudService.findAll<FieldModuleTerm>({
      resourceName: 'sampleFormValues',
      queryParams: {
        codeIso: language.isoCode,
        elementName: uniqueFields,
      },
      pathParams: {
        module: module.id,
      },
      pageReq: PageReqBuilder.unpaged([
        ['elementName', SortDirection.ASC],
        ['sortOrder', SortDirection.ASC],
      ]),
    }));

    this._comboValues = {};
    page.content.forEach(item => {
      if (!this._comboValues[item.elementName]) {
        this._comboValues[item.elementName] = [];
      }
      this._comboValues[item.elementName].push(item);
    });

  }

  private async buildDatasets() {
    this.datasetForm = new FormGroup<DatasetForm>({
      common: new FormGroup<DatasetFormGroup>({
        datasetId: new FormControl(null),
        resultId: new FormControl(null),
      }),
      result: new FormArray<FormGroup<DatasetFormGroup>>([]),
    });
    await this.buildCommonDataset();
    await this.buildResultDataset();
  }

  private async buildCommonDataset() {
    const positions = Object.values(this._positions)
      .filter(v => !v.positionIsRepeatable)
      .sort((a, b) => a.id - b.id);

    const numberOfGroups = positions.length || 0;
    const containerType = DatasetContainerType.COMMON;

    const uiGroups: DatasetGroupContainerUI[] = [];
    const commonDatasetId = this.srcDataset?.common?.datasetId ?? null;
    let formGroup = new FormGroup<DatasetFormGroup>({
      datasetId: new FormControl(commonDatasetId),
      resultId: new FormControl(null),
    });

    for (let i = 0; i < numberOfGroups; i++) {
      const position = positions[i];
      const groupUID = SimpleUidGenerator.createUid('G');
      const groupPosition = DatasetCoordinates.construct(containerType, [groupUID]);

      // obtener los datos del SRC
      // o de la plantilla
      // o de la programación
      // o de los datos preconfigurados para una nueva muestra
      const { rows, controls } = await this.buildDatasetContents({
        groupPosition,
        positionType: position.type,
        previousData: this.srcDataset?.common,
      });

      formGroup = new FormGroup<DatasetFormGroup>({
        ...formGroup.controls,
        ...controls,
      });

      uiGroups.push(new DatasetGroupContainerUI({
        formGroup,
        containerType: containerType,
        items: Object.values(rows),
        order: i,
        index: i,
        label: position.name,
        position: groupPosition,
      }));
    }

    this.datasetContainer.common.next(uiGroups);
    this.datasetForm.controls.common = formGroup;
  }

  private async buildResultDataset() {
    if (!this.srcDataset?.results) return;

    const position = this._positions[FieldPositionType.RESULTADOS];

    const numberOfGroups = this.srcDataset?.results.length ?? 0;
    const containerType = DatasetContainerType.RESULT;

    const uiGroups: DatasetGroupContainerUI[] = [];
    const formGroups: FormGroup<DatasetFormGroup>[] = [];

    for (let i = 0; i < numberOfGroups; i++) {
      const groupUID = SimpleUidGenerator.createUid('G');
      const groupPosition = DatasetCoordinates.construct(containerType, [groupUID]);

      // obtener los datos del SRC
      // o de la plantilla
      // o de la programación
      // o de los datos pre-configurados para una nueva muestra
      const previousData = this.srcDataset.results[i];
      const { rows, controls } = await this.buildDatasetContents({
        groupPosition,
        positionType: position.type,
        previousData,
      });

      const formGroup = new FormGroup<DatasetFormGroup>({
        datasetId: new FormControl(previousData.datasetId),
        resultId: new FormControl(previousData.resultId),
        ...controls,
      });

      formGroups.push(formGroup);
      uiGroups.push(new DatasetGroupContainerUI({
        formGroup,
        containerType: containerType,
        items: Object.values(rows),
        order: i,
        index: i,
        label: position.name,
        position: groupPosition,
      }));
    }

    this.datasetContainer.result.next(uiGroups);
    this.datasetForm.controls.result = new FormArray(formGroups, [Validators.required, Validators.minLength(1)]);
  }

  private async buildDatasetContents(opts: {
    groupPosition: string;
    positionType: string;
    previousData?: SampleDatasetCtx;
  }) {
    const { positionType, groupPosition, previousData } = opts;

    const fields = this._fieldsByPosition[positionType]
      .map(elementName => this._fieldsByName[elementName])
      .sort((a, b) => {
        if (a.positionRow < b.positionRow) return -1;
        if (a.positionRow > b.positionRow) return 1;
        if (a.positionColumn < b.positionColumn) return -1;
        if (a.positionColumn > b.positionColumn) return 1;
        return 0;
      });

    const rows: Record<number, DatasetRowContainerUI> = {};
    const controls: Record<string, FormControl<DatasetFormValue>> = {};

    for (const field of fields) {
      const rowPosition = `${ groupPosition }#${ field.positionRow }`;
      const colPosition = `${ rowPosition }#${ field.elementName }`;

      if (!rows[field.positionRow]) rows[field.positionRow] = new DatasetRowContainerUI({
        order: field.positionRow,
        index: field.positionRow,
        position: rowPosition,
      });

      const value = await this.getFieldValue({ field, previousData });
      const columnCtrl = new FormControl<DatasetFormValue>(value);
      if (field.required) {
        columnCtrl.addValidators(Validators.required);
      }

      const columnUI = new DatasetColumnUI({
        formCtrl: columnCtrl,
        elementName: field.elementName,
        elementType: field.elementType,

        label: field.elementLabel,
        description: field.elementDescription,
        order: field.positionColumn,
        index: field.positionColumn,
        position: colPosition,

        hidden: field.hidden,
        comboMultiselect: field.multiple,
        comboValues: this._comboValues[field.elementName],

        catalogueId: field.extCatalogueId || undefined,
        hierarchyId: field.extHierarchyId || undefined,
      });

      const subscription = columnCtrl.valueChanges
        .pipe(debounceTime(500), distinctUntilChanged())
        .subscribe(() => this.columnValueChanged(columnUI));
      this._activeSubscriptions.add(subscription);

      controls[columnUI.ctrlName] = columnCtrl;
      rows[field.positionRow].pushItem(columnUI);
    }

    return {
      controls,
      rows: Object.values(rows),
    };
  }

  private async getFieldValue(opts: {
    field: VFieldModule;
    previousData?: Partial<SampleDatasetCtx>;
  }): Promise<DatasetFormValue> {
    const { field, previousData } = opts;

    let val: DatasetFormValue = null;
    if (previousData) {
      val = previousData[field.elementName] ?? null;
    }

    if (!val && field.valueDefault) {
      val = field.valueDefault;
    }

    const { elementType } = field;
    if (elementType === FieldElementType.COMBO && this._comboValues[field.elementName]?.length) {
      if (field.multiple) {
        const values = (val + '').split(field.valueSeparator ?? DEFAULT_VALUE_SEPARATOR);
        const terms = this._comboValues[field.elementName];
        val = terms.filter(({ code }) => values.includes(code));
      } else {
        const found = this._comboValues[field.elementName].find(v => v.code === val);
        if (found) val = found;
      }
    }

    // o catalogo { code, description }
    // ??? como obtener la descripción
    if (elementType === FieldElementType.CAT) {
      val = {
        code: val as string,
        description: val as string,
      };
    }

    if (typeof val === 'string') {
      val = await this.resolveDefaultValue(val);
    }

    return val;
  }

  // Algunos valores pueden ser códigos estáticos que hay que resolver
  // este método debería realizar esa resolución
  private async resolveDefaultValue(value: string) {
    let result: DatasetFormValue = value;
    switch (value) {
      case DefaultValuesCodes.YEAR:
        result = new Date().getFullYear();
        break;
      case DefaultValuesCodes.PREV_YEAR:
        result = new Date((await firstValueFrom(this.ctx.season$)).efsaDeliveryStart).getFullYear();
        break;
    }
    return Promise.resolve(result);
  }

  private compactValue(opts: {
    value: DatasetFormValue;
    field: VFieldModule;
  }): string | null {
    const { value, field } = opts;

    if (!value) return null;

    if (Array.isArray(value)) {
      const separator = field.valueSeparator || DEFAULT_VALUE_SEPARATOR;
      return value
        .map(v => this.compactValue({ field, value: v }))
        .join(separator);
    }

    if (typeof value === 'object') {
      return this.compactValue({ field, value: value.code });
    }

    return value.toString();
  }

  private async columnValueChanged(column: DatasetColumnUI) {
    await this.runAssociationRules(column);
    this.runAssignmentRules(column);
  }

  private columnValueChangedError(column: DatasetColumnUI, err: unknown) {
    console.error(`Column ${ column.ctrlName } (${ column.position }) has en error`, err);
  }

  private async runAssociationRules(column: DatasetColumnUI) {
    const field = this._fieldsByName[column.elementName];
    const value = column.formCtrl.value;
    const formValue = this.compactValue({ field, value });
    const parts = DatasetCoordinates.destruct(column.position);
    const [type, groupUID] = parts;

    const rulesByTarget = this._associationsByElement[column.elementName] || [];
    const entries = Object.entries(rulesByTarget);
    for (let i = 0; i < entries.length; i++) {
      const [targetElementName, rules] = entries[i];

      const applicableRuleIdx = rules.findIndex(rule => formValue === rule.sourceExpectedValue);

      let hidden: boolean;
      let required: boolean;
      if (applicableRuleIdx >= 0) {
        hidden = false;
        required = rules[applicableRuleIdx].targetIsRequired;
      } else {
        hidden = true;
        required = false;
      }

      const targetField = this._fieldsByName[targetElementName];

      const groupPosition = DatasetCoordinates.construct(type, [groupUID]);
      const targetPosition = DatasetCoordinates.construct(type, [groupUID, targetField.positionRow, targetField.elementName]);

      const container = type === DatasetContainerType.COMMON
        ? this.datasetContainer.common.value.find(v => v.position === groupPosition)
        : this.datasetContainer.result.value.find(v => v.position === groupPosition);

      if (!container) return;

      container.update({
        targetPosition,
        hidden,
        required,
      });

      if (hidden) {
        const ctrl = container.formGroup.get(targetElementName);
        const defaultValue = await this.getFieldValue({ field: targetField });
        if (ctrl) ctrl.reset(defaultValue);
      }
    }
  }

  private runAssignmentRules(column: DatasetColumnUI) {
    const value = column.formCtrl.value;
    const parts = DatasetCoordinates.destruct(column.position);
    const [type, groupUID] = parts;

    const rules = this._assignmentsByElement[column.elementName] || [];
    rules.forEach(rule => {
      const targetField = this._fieldsByName[rule.targetElementName];
      const targetCtrlName = DatasetColumnUI.constructCtrlName(targetField.elementName);

      const groupPosition = DatasetCoordinates.construct(type, [groupUID]);

      let container: FormGroup<DatasetFormGroup> | null = null;
      if (type === DatasetContainerType.COMMON) {
        container = this.datasetForm.controls.common;
      } else {
        const dataset = this.datasetContainer.result.value.find(v => v.position === groupPosition);
        if (dataset) container = dataset.formGroup;
      }

      if (!container) return;

      const ctrl = container.get(targetCtrlName);
      if (ctrl) ctrl.patchValue(value);
    });
  }

  private async configureBusinessRules() {
    // Aplicar las validaciones en los elementos individuales del formulario
    Object.entries(this._businessRuleByGroup.single).forEach(([elementName, evaluators]) => {
      const field = this._fieldsByName[elementName];
      if (!field) return;
      const validators: AsyncValidatorFn[] = evaluators.map(e => this.compileSingleValidator(e));
      const ctrlName = DatasetColumnUI.constructCtrlName(elementName);
      if (field.positionIsRepeatable) {
        this.datasetForm.controls.result.controls.forEach(group => {
          const ctrl = group.get(ctrlName);
          if (ctrl) ctrl.setAsyncValidators(validators);
        });
      } else {
        const ctrl = this.datasetForm.controls.common.get(ctrlName);
        if (ctrl) ctrl.setAsyncValidators(validators);
      }
    });

    const commonValidators: AsyncValidatorFn[] = this._businessRuleByGroup.common.map(e => this.compileGroupValidator(e));
    this.datasetForm.controls.common.setAsyncValidators(commonValidators);

    const resultValidators: AsyncValidatorFn[] = this._businessRuleByGroup.result.map(e => this.compileGroupValidator(e));
    this.datasetForm.controls.result.controls.forEach(group => group.setAsyncValidators(resultValidators));

    const globalValidators: AsyncValidatorFn[] = this._businessRuleByGroup.global.map(e => this.compileGlobalValidator(e));
    this.datasetForm.setAsyncValidators(globalValidators);
  }

  private compileSingleValidator(evaluator: BusinessRuleEvaluator): AsyncValidatorFn {
    return async (abstractCtrl: AbstractControl) => {
      // console.info(`Executing simple validator for ${ evaluator.code }`);
      if (!abstractCtrl) return null;

      const ctrl = abstractCtrl as FormControl<DatasetFormValue>;
      const [elementName] = evaluator.checkedElements;
      const ctx: BusinessRuleExpressionCtx = {};
      ctx[elementName] = this.compactValue({
        value: ctrl.value,
        field: this._fieldsByName[elementName]
      });
      const result = evaluator.evaluate(ctx);
      // console.info(`Evaluated "${ evaluator.code }"`, result);
      return result;
    };
  }

  private compileGroupValidator(evaluator: BusinessRuleEvaluator): AsyncValidatorFn {
    return async (abstractCtrl: AbstractControl) => {
      // console.info(`Executing group validator for ${ evaluator.code }`);
      if (!abstractCtrl) return null;
      const ctrl = abstractCtrl as FormGroup<DatasetFormGroup>;

      const ctx: BusinessRuleExpressionCtx = {};
      for (const [key, value] of Object.entries(ctrl.value)) {
        const elementName = DatasetColumnUI.destructCtrlName(key);
        ctx[elementName] = this.compactValue({
          field: this._fieldsByName[elementName],
          value: value || null,
        });
      }

      const result = evaluator.evaluate(ctx);
      // console.info(`Evaluated "${ evaluator.code }"`, result);
      return result;
    };
  }

  private compileGlobalValidator(evaluator: BusinessRuleEvaluator): AsyncValidatorFn {
    return async (abstractCtrl: AbstractControl) => {
      // console.info(`Executing global validator for ${ evaluator.code }`);
      if (!abstractCtrl) return null;

      const ctrl = abstractCtrl as FormGroup<DatasetForm>;
      const commonCtrl = ctrl.controls.common;
      let result: ValidationErrors | null = null;
      // por cada resultado, crear ctx y evaluar
      // el resultado final sería la combinación de todos los resultados
      // o en el primer error encontrado, parar
      for (const resultCtrl of ctrl.controls.result.controls) {
        // const ctx: BusinessRuleExpressionCtx = {};

        // mágia

        // const localResult = evaluator.evaluate(ctx);
        // if (localResult != null) {
        //   result = localResult;
        // }
      }

      // console.info(`Evaluated "${ evaluator.code }"`, result);
      return result;
    };
  }

  private getFormErrors(form: AbstractControl) {
    if (form instanceof FormControl) {
      // Return FormControl errors or null
      return form.errors ?? null;
    }

    if (form instanceof FormGroup) {
      // Form group can contain errors itself, in that case add'em
      const formErrors = { ...form.errors };
      Object.keys(form.controls).forEach(key => {
        // Recursive call of the FormGroup fields
        const ctrl = form.get(key);
        if (ctrl) {
          const error = this.getFormErrors(ctrl);
          if (error !== null) {
            // Only add error if not null
            formErrors[key] = error;
          }
        }
      });
      // Return FormGroup errors or null
      return Object.keys(formErrors).length > 0
        ? formErrors
        : null;
    }

    if (form instanceof FormArray) {
      const formErrors = { ...form.errors };
      form.controls.forEach((ctrl, index) => {
        formErrors[index] = this.getFormErrors(ctrl);
      });
      return formErrors;
    }

    return null;
  }
}
