import { Injectable } from "@angular/core";
import { FormArray, FormControl, FormGroup, Validators } from "@angular/forms";
import { VFieldModule } from "@libs/sdk/field-module";
import { Module } from "@libs/sdk/module";
import { DataQueryConfigForm, NamedDataQuery } from './data-query.model';
import { ControlsOf } from "@libs/commons";
import { BehaviorSubject } from "rxjs";
import { CrudImplService, PageReqBuilder, RequestConfig } from "@libs/crud-api";

@Injectable({ providedIn: 'root' })
export class DataQueryService {

    private _currentMode = new BehaviorSubject<'config' | 'view'>('config');
    private _fields = new BehaviorSubject<VFieldModule[]>([]);

    private _form = new FormGroup<DataQueryConfigForm>({
        module: new FormControl<Module | null>(null, [Validators.required]),
        usePreviousQuery: new FormControl<boolean>(false,{
            nonNullable: true,
            validators: [Validators.required],
        }),
        dataQuery: new FormGroup<ControlsOf<NamedDataQuery>>({
            id: new FormControl(null),
            name: new FormControl(null),
        }),

        fields: new FormArray<FormControl<VFieldModule>>([]),
        // indicators: new FormArray<FormControl<?>>([]),
        // operations: new FormArray<FormGroup<{}>>([]),,
    });

    constructor(private crudService: CrudImplService) {
    }

    get currentMode$() {
        return this._currentMode.asObservable();
    }

    get form() {
        return this._form;
    }

    get fields$() {
        return this._fields.asObservable();
    }

    saveConfiguration() {
        // guardar la configuración de la consulta
    }

    destroy() {
        // resetear datos no reutilizables
        // form sobretodo
    }

    loadFieldsModules() {
        // no cargar si no está configurado el módulo
        // si el módulo no cambia y los datos están cargados, omitir el proceso

        //if (this._form.value.module?.id != undefined) {
        const config: RequestConfig = {
            resourceName: 'fieldModules',
            queryParams: {
                moduleId: this.form.value.module?.id || 2,
            },
            pageReq: PageReqBuilder.unpaged(),
        };
        this.crudService.findAll<VFieldModule>(config).subscribe(page => this._fields.next(page.content));
        //}
        console.log(this.form.value.module?.id);
        console.log(this._fields);

    }
}