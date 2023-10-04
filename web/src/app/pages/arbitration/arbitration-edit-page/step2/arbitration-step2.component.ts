import {Component} from '@angular/core';
import {ComponentStatus} from '@libs/commons';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {B64EncodedFile} from "@libs/file";
import {SampleSeason} from "@libs/sdk/sample-season";
import {Module} from "@libs/sdk/module";
import {ScopeView} from "@libs/sdk/scope";
import {ActivatedRoute, Router} from "@angular/router";

type FileUploadForm = {
    module: FormControl<Module | null>,
    season: FormControl<SampleSeason | null>,
    scope: FormControl<ScopeView | null>,
    file: FormControl<B64EncodedFile | null>,
};
const SUCCESS_CLOSE_TIMEOUT = 2000;
const ERROR_CLOSE_TIMEOUT = 4000;

@Component({
    selector: 'tsw-arb-edit-step2',
    templateUrl: './arbitration-step2.component.html',
    styleUrls: ['./arbitration-step2.component.scss']
})
export class ArbitrationStep2Component {

    readonly process = new ComponentStatus('IDLE');
    readonly form: FormGroup<FileUploadForm>;
    private _startValue: undefined;
    skipSaveConfirmation = true;
    redirectAfterSavePath = ["../sign"]

    readonly pickerOpts = {
        type: 'file',
        accept: 'text/xml'
    };

    constructor(
        private fb: FormBuilder,
        protected route: ActivatedRoute,
        protected router: Router,
    ) {
        this.form = this.buildForm();

    }

    private buildForm() {
        return this.fb.group<FileUploadForm>({
            module: this.fb.control(null, ),
            season: this.fb.control(null, ),
            scope: this.fb.control(null, ),
            file: this.fb.control(null, ),
        });
    }

    submitForm() {
        // if (this.form.invalid) {
            // this.notification.show({ message: 'text.other.pleaseReview' });
            // return;
        // }
        this.save();
    }

    protected  async save() {


        await this.router.navigate(this.redirectAfterSavePath, {relativeTo: this.route});

    }

    get startValue() {
        return this._startValue;
    }

    resetForm() {
        this.form.reset(this.startValue);
    }


}
