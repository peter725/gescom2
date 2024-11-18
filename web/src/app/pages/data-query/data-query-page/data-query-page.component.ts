import { Component, OnDestroy, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { AppContextService } from "@base/shared/app-context";
import { firstValueFrom } from "rxjs";
import { DataQueryService } from "./data-query.service";
import { DataQueryConfigForm } from "./data-query.model";

@Component({
    // eslint-disable-next-line @angular-eslint/component-selector
    selector: "tsw-data-query-page",
    templateUrl: "./data-query-page.component.html",
    styleUrls: ["./data-query-page.component.scss"],
})
export class DataQueryPageComponent implements OnInit, OnDestroy {

    readonly form: FormGroup<DataQueryConfigForm>;
    readonly resourceName = 'dataQuery';

    /* private fields$: BehaviorSubject<VTulsaFieldModule>;
    private configurationForm: FormGroup<DataQueryConfigForm>; */

    constructor(
        private fb: FormBuilder,
        private ctx: AppContextService,
        private service: DataQueryService,
    ) {
        this.form = service.form;
    }

    async ngOnInit() {
        const scope = await firstValueFrom(this.ctx.scope$);
    }

    isModuleSelected(): boolean {
        return this.service.form?.get('module')?.value !== null;
    }

    loadFields() {
        if (this.form.value.module != undefined) {
            this.service.loadFieldsModules();
        }
    }

    ngOnDestroy() {
        this.service.destroy();
    }
}