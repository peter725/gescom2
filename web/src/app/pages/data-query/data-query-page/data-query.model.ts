import { FormGroup, FormControl, FormArray } from '@angular/forms';
import { ControlsOf } from '@libs/commons';
import { VFieldModule } from '@libs/sdk/field-module';
import { Module } from '@libs/sdk/module';


export interface DataQueryConfigForm {
    dataQuery: FormGroup<ControlsOf<NamedDataQuery>>;
    usePreviousQuery: FormControl<boolean>;
    module: FormControl<Module | null>;
    fields: FormArray<FormControl<VFieldModule>>;
    // indicators: new FormArray<FormControl<?>>([]),
    // operations: new FormArray<FormGroup<{}>>([]),,
}

export interface NamedDataQuery {
    id: number | null;
    name: string;
}
