import { Component, OnInit } from "@angular/core";
import { CrudImplService } from "@libs/crud-api";
import { VFieldModule } from "@libs/sdk/field-module";
import { Observable } from "rxjs";
import { FormGroup, FormBuilder, FormArray, FormControl } from "@angular/forms";
import { AppContextService } from "@base/shared/app-context";
import { DataQueryService } from '../../data-query.service';
import { DataQueryConfigForm } from '../../data-query.model';
import { MatCheckboxChange } from "@angular/material/checkbox";

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: "tsw-data-query-config-fields",
  templateUrl: "./data-query-config-fields.component.html",
  styleUrls: ["./data-query-config-fields.component.scss"],
})
export class DataQueryConfigFieldsComponent implements OnInit {

  readonly form: FormGroup<DataQueryConfigForm>;
  readonly fields$: Observable<VFieldModule[]>;

  constructor(
    private crudService: CrudImplService,
    private fb: FormBuilder,
    private ctx: AppContextService,
    private service: DataQueryService,
  ) {
    this.form = service.form;
    this.fields$ = service.fields$;
  }

  select(ev: MatCheckboxChange, item: VFieldModule) {
    const fieldsArray = this.form.get('fields') as FormArray;
    if (ev.checked) {
      fieldsArray.push(new FormControl(item));
    } else {
      const index = fieldsArray.controls.findIndex(control => control.value === item);
      if (index !== -1) {
        fieldsArray.removeAt(index);
      }
    }
    console.log(fieldsArray);
  }

  ngOnInit() {
    console.log('Entra al init de campos');

    if (this.form.value.module != undefined) {
      this.service.loadFieldsModules();
    }
  }

}
