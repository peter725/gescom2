import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { ColumnSource, ColumnSrc } from '@tulsa/app/shared/collections';
import { BusinessRule } from '@tulsa/app/shared/sample/business-rule';
import { BusinessRuleFactory } from '@tulsa/app/shared/sample/business-rule.factory';
import { BusinessRulesValidations } from '@tulsa/app/shared/sample/business-rule.validator';
import { SampleFormData, SampleFormResult } from '@tulsa/app/shared/sample/models';
import { SampleForm } from '@tulsa/app/shared/sample/sample-form';
import { EvaluableBusinessRule } from '@tulsa/libs/sdk/evaluable-business-rules';
import { FieldPositionType, VTulsaFieldModule } from '@tulsa/libs/sdk/field-module';
import { FieldModuleAssociation } from '@tulsa/libs/sdk/field-module-association';
import { TulsaFieldModuleTerm } from '@tulsa/libs/sdk/field-module-term';
import { NO_LANGUAGE, TulsaLanguage } from '@tulsa/libs/sdk/language';
import { TulsaSample, TulsaSampleData, TulsaSampleResult, TulsaSampleResultData } from '@tulsa/libs/sdk/sample';
import { ReplaySubject, takeUntil } from 'rxjs';
import { SampleFormFactory } from './sample-form.factory';
import {
  SampleUi,
  SampleUiElement,
  SampleUiElementGroup,
  SampleUiField,
  SampleUiRegularSection,
  SampleUiResultSection,
  SampleUiSection,
} from './sample-ui';
import { SampleUiFactory } from './sample-ui.factory';
import { buildSampleUIDataField, formatControlName, LIST_SECTION_KEYS, SAFE_RESULT_COLS } from './utils';


export class OngoingSampleForm extends SampleForm {
  private readonly destroyed$ = new ReplaySubject<void>(1);

  /**
   * Definición de los campos con valores por defecto
   */
  private dataFields: SampleUiField[] = [];
  private resultFields: SampleUiField[] = [];
  private mappedAssociations: Record<number, FieldModuleAssociation[]> = {};

  private sampleFormFactory = new SampleFormFactory({});
  private uiFactory = new SampleUiFactory();

  constructor(
    /**
     * Definición de los campos del formulario
     */
    private readonly fields: VTulsaFieldModule[],
    /**
     * Idioma del usuario autenticado
     */
    private readonly language: TulsaLanguage,
    /**
     * Datos de la muestra a visualizar
     * En caso de ser nueva, no tendrá datos ni resultados
     */
    sample: Readonly<TulsaSample>,
    /**
     * Reglas de asociación que aplicamos a los campos
     */
    private associationRules: FieldModuleAssociation[] = [],
    /**
     * Reglas de validación que aplicamos a los campos
     */
    private businessRules: EvaluableBusinessRule[],
    private fieldValues: TulsaFieldModuleTerm[],
  ) {
    super(sample);
    this.initialize();
  }

  initialize() {
    this.process.status = 'LOAD';

    this.processFields();
    this.processComboValues();
    this.processAssociationRules();
    this.sampleFormFactory = new SampleFormFactory(this.comboValues);

    this.compileDynamicColumns();

    this.compileForm();
    this.compileUI();
    this.processBusinessRules();

    this.process.status = 'IDLE';
  }

  /**
   * Cleanup sample data before destroy
   */
  destroy() {
    this.destroyed$.next();
  }

  addResult(section: SampleUiResultSection, numOfResults = 1) {
    if (!numOfResults || numOfResults < 1) return;
    // Build a new form group for SampleFormResult
    // Create form controls for every field in results list
    // Push to results array

    const uiGroups: SampleUiElementGroup[] = [];
    // let lastGroupIndex = section.length - 1;
    for (let i = 0; i < numOfResults; i++) {
      const group = this.sampleFormFactory.resultGroup({
        id: 0,
        sampleId: this.sample.id,
        data: [],
      });
      const isLast = i === (numOfResults - 1);
      this._srcForm.controls.results.push(group, { emitEvent: isLast });

      const fields = [...this.resultFields];
      const [first] = fields;
      const groupKey = first.positionType;
      // uiGroups.push(new SampleUiElementGroup(fields, groupKey, ++lastGroupIndex));
    }
    section.addResults(uiGroups);
  }

  removeResult(index: number) {
    // remove a result from the form
    // sample value will be removed at compile time
    // remove event listeners if possible
    if (!this._srcForm.controls.results.at(index)) return;
    this._srcForm.controls.results.removeAt(index);
  }

  getValue(): Partial<TulsaSample> {
    /*if (this.form.invalid) {
      throw new Error('Invalid sample data!');
    }*/

    return {
      id: this.sample.id || undefined,
      module: this.sample.module,
      season: this.sample.season,
      state: this.sample.state,
      sampleState: this.sample.sampleState,
      pubCode: this.sample.pubCode,
      scopeCode: this.sample.scopeCode,
      data: this.mapDataFormToModel(),
      results: this.mapResultFormToModel(),
    };
  }

  private processFields() {
    const data: SampleUiField[] = [];
    const results: SampleUiField[] = [];
    this.fields.forEach(field => {
      const isResult = LIST_SECTION_KEYS.includes(field.positionType);
      const value = buildSampleUIDataField(field);
      isResult ? results.push(value) : data.push(value);
    });
    this.dataFields = [...data];
    this.resultFields = [...results];

    console.info('Sample fields compiled', { data, results });
  }

  private processComboValues() {
    const formattedValues: Record<string, TulsaFieldModuleTerm[]> = {};
    this.fieldValues.forEach(value => {
      if (!formattedValues[value.elementName]) {
        formattedValues[value.elementName] = [];
      }
      formattedValues[value.elementName].push(value);
    });
    this._comboValues = formattedValues;
    console.info('Sample combo data compiled', { formattedValues });
  }

  private processAssociationRules() {
    this.mappedAssociations = this.associationRules.reduce<Record<number, FieldModuleAssociation[]>>((acc, value) => {
      let association = acc[value.parentFieldModuleId];
      if (!association) {
        association = [];
        acc[value.parentFieldModuleId] = association;
      }
      association.push(value);
      return acc;
    }, {});
  }

  private processBusinessRules() {
    const validations = this.businessRules
      .map(rule => {
        rule.checkedElements = rule.checkedElements.map(formatControlName);
        rule.verifiedElements = rule.verifiedElements.map(formatControlName);
        return BusinessRuleFactory.compileRule(rule);
      })
      .reduce<{
        single: Record<string, BusinessRule[]>,
        multi: BusinessRule[],
      }>((acc, rule) => {
        if (rule.checkedElements.length > 1) {
          acc.multi.push(rule);
        } else {
          const [first] = rule.checkedElements;
          if (!acc.single[first]) acc.single[first] = [];
          acc.single[first].push(rule);
        }
        return acc;
      }, { single: {}, multi: [] });
    console.info(validations);

    const fields = [...this.dataFields, ...this.resultFields].reduce<Record<string, SampleUiField>>((acc, field) => {
      acc[field.controlName] = field;
      return acc;
    }, {});


    Object.entries(validations.single).forEach(([ctrlName, rules]) => {
      const field = fields[ctrlName];
      if (!field) return;
      const isResult = LIST_SECTION_KEYS.includes(field.positionType);

      if (isResult) {
        this.form.controls.results.controls.forEach(resultCtrl => {
          const ctrl = resultCtrl.controls.data.get(ctrlName);
          if (ctrl) {
            rules.forEach(rule => {
              const [first] = rule.checkedElements;
              const field = fields[first];
              ctrl.addAsyncValidators(BusinessRulesValidations.validateCtrl(rule, field));
            });
          }
        });
      } else {
        const ctrl = this.form.controls.data.get(ctrlName);
        if (ctrl) {
          rules.forEach(rule => {
            const [first] = rule.checkedElements;
            const field = fields[first];
            ctrl.addAsyncValidators(BusinessRulesValidations.validateCtrl(rule, field));
          });
        }
      }
    });

    Object.values(validations.multi).forEach(rule => {
      const fieldExists = rule.checkedElements.every(elementName => fields[elementName] != null);
      if (!fieldExists) return;

      const containsResults = rule.checkedElements.some(elementName => fields[elementName]?.positionType === FieldPositionType.RESULTADOS);
      if (containsResults) this.form.addAsyncValidators(BusinessRulesValidations.validateGlobal(rule, fields));
      else this.form.controls.data.addAsyncValidators(BusinessRulesValidations.validateGroup(rule, fields));
    });
  }

  private compileForm() {
    // Formulario inicial de datos
    const form = new FormGroup<SampleFormData>({
      data: this.sampleFormFactory.formGroup(),
      results: new FormArray<FormGroup<SampleFormResult>>([]),
    });

    // Genera los campos comunes del formulario
    this.dataFields.forEach(field => {
      const entry = this.sample.data.find(({ fieldId }) => fieldId === field.id);
      const ctrl = this.sampleFormFactory.formControl(entry?.value, field);
      ctrl.valueChanges.pipe(takeUntil(this.destroyed$)).subscribe({
        next: value => this.valueChange(field, ctrl, value),
        error: err => this.valueChangeError(field, ctrl, err),
      });
      form.controls.data.addControl(field.controlName, ctrl);
    });

    // Genera los resultados y sus campos
    this.sample.results.forEach(result => {
      const group = this.sampleFormFactory.resultGroup(result);
      this.resultFields.forEach(field => {
        const entry = result.data.find(({ fieldId }) => fieldId === field.id);
        const ctrl = this.sampleFormFactory.formControl(entry?.value, field);
        ctrl.valueChanges.pipe(takeUntil(this.destroyed$)).subscribe({
          next: value => this.valueChange(field, ctrl, value),
          error: err => this.valueChangeError(field, ctrl, err),
        });
        group.controls.data.addControl(field.controlName, ctrl);
      });
      form.controls.results.push(group);
    });

    // Formulario generado con todos los datos
    this._srcForm = form;
    console.info('Data form is ready', this.form.value);
  }

  private compileUI() {
    const fieldSrc = [...this.dataFields, ...this.resultFields];
    const sampleUiSections: SampleUiSection[] = [];

    // create a field list for each section organized by positionType
    const fieldsBySection = fieldSrc.reduce<Record<string, SampleUiField[]>>((acc, field) => {
      if (!acc[field.positionType]) {
        acc[field.positionType] = [];
      }
      acc[field.positionType].push({ ...field });
      return acc;
    }, {});

    // create field lists and element groups for each section
    Object.entries(fieldsBySection).forEach(([sectionKey, fields], index) => {
      let section: SampleUiSection;
      let label = '';
      let groupKey = '';
      if (fields.length) {
        const [first] = fields;
        label = first.positionName;
        groupKey = `${ first.positionType }`;
      }
      if (sectionKey === FieldPositionType.RESULTADOS) {
        const groups = this.form.controls.results.controls.map((resultCtrl, index) => {
          const { groupKey } = resultCtrl.getRawValue();
          const elements: SampleUiElement[] = [];
          Object.entries(resultCtrl.controls.data.controls).forEach(([ctrlName, ctrl]) => {
            const field = fields.find(field => field.controlName === ctrlName);
            if (field) elements.push(new SampleUiElement(field, ctrl));
          });
          return new SampleUiElementGroup(elements, groupKey, index);
        });
        // crear campos para cada grupo vinculados con su FormControl
        // const groups = this.sample.results.map((_, index) => new SampleUiElementGroup(fields, groupKey, index));
        section = new SampleUiResultSection(groups, sectionKey, label, index);
      } else {
        const elements: SampleUiElement[] = [];
        Object.entries(this.form.controls.data.controls).forEach(([ctrlName, ctrl]) => {
          const field = fields.find(field => field.controlName === ctrlName);
          if (field) elements.push(new SampleUiElement(field, ctrl));
        });
        const group = new SampleUiElementGroup(elements, groupKey);
        section = new SampleUiRegularSection(group, sectionKey, label, index);
      }

      sampleUiSections.push(section);
    });

    const ui = new SampleUi(sampleUiSections);
    console.info(ui);
    this._ui = ui;
  }

  private compileDynamicColumns() {
    const columns: ColumnSrc[] = this.resultFields
      .filter(field => SAFE_RESULT_COLS.includes(field.elementName) || !field.hidden)
      .map(field => ({
        name: field.elementLabel,
        property: field.elementName,
        visible: SAFE_RESULT_COLS.includes(field.elementName),
      }))
      .sort(col => col.visible ? -1 : 1);
    this._resultColumns = new ColumnSource(columns);
    console.info('Dynamic columns are ready', columns);
  }

  private valueChange<T>(srcField: Readonly<SampleUiField>, srcCtrl: Readonly<FormControl<T>>, value: Readonly<T>) {
    // procesar sus asociaciones
    // procesar business rules de tipo warning?

    // const rules = this.mappedAssociations.get(srcField.id);
    // const hasValue = !!srcCtrl.value;
    // if (!rules?.length) return;
    //
    // rules.forEach(rule => {
    //   const found = this.fields.find(f => f.id === rule.fieldModuleId);
    //   if (!found) return;
    //
    //   const field = { ...found };
    //   field.hidden = false;
    //   field.required = rule.required;
    //   const sectionKey = field.positionType;
    //   const groupKey = sectionKey;
    //   const rowKey = field.positionRow + '';
    //
    //   this.ui.update({
    //     fieldId: field.id,
    //     sectionKey,
    //     groupKey,
    //     rowKey,
    //     change: {
    //       hidden: !hasValue,
    //     },
    //   });
    // });
  }

  private valueChangeError<T>(srcField: Readonly<SampleUiField>, srcCtrl: Readonly<FormControl<T>>, err: Readonly<unknown>) {
    console.error(`${ srcField.controlName } value changed has returned an error because `, err);
  }

  private mapDataFormToModel() {
    const data = this.form.getRawValue().data;
    const models: Partial<TulsaSampleData>[] = [];
    Object.entries(data).forEach(([key, srcValue]) => {
      const field = this.dataFields.find(f => f.controlName === key);
      if (!field) {
        return;
      }
      const value = this.sampleFormFactory.simplifyValue(srcValue, field);
      if (value != null) {
        const sampleData = this.sample.data.find(d => d.fieldId === field.id);
        models.push({
          id: sampleData?.id || undefined,
          sampleId: this.sample.id || undefined,
          fieldId: field.id,
          languageId: field.multiLanguage ? this.language.id : NO_LANGUAGE,
          value,
        });
      }
    });
    return models as TulsaSampleData[];
  }

  private mapResultFormToModel() {
    const data = this.form.getRawValue().results;
    const models: Partial<TulsaSampleResult>[] = [];
    Object.values(data).forEach(srcResult => {
      const resultId = srcResult.id || undefined;
      const data: Partial<TulsaSampleResultData>[] = [];
      Object.entries(srcResult.data).forEach(([key, srcValue]) => {
        const field = this.resultFields.find(f => f.controlName === key);
        if (!field) {
          return;
        }
        const value = this.sampleFormFactory.simplifyValue(srcValue, field);
        if (value != null) {
          const prevResult = this.sample.results.find(d => d.id === resultId);
          const resultData = (prevResult?.data || []).find(r => r.fieldId === field.id);
          data.push({
            id: resultData?.id || undefined,
            fieldId: field.id,
            languageId: field.multiLanguage ? this.language.id : NO_LANGUAGE,
            resultId: resultId,
            value,
          });
        }
      });
      models.push({
        id: resultId,
        sampleId: this.sample.id || undefined,
        data: (data as TulsaSampleResultData[]),
      });
    });
    return models as TulsaSampleResult[];
  }

}
