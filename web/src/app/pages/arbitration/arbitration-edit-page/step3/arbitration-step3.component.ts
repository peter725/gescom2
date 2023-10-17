import {Component} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ComponentStatus} from "@libs/commons";
import {Module} from "@libs/sdk/module";

type FileUploadForm = {
    module: FormControl<Module | null>,
};

@Component({
    selector: 'tsw-arb-edit-step3',
    templateUrl: './arbitration-step3.component.html',
    styleUrls: ['./arbitration-step3.component.scss']
})
export class ArbitrationStep3Component {
    readonly process = new ComponentStatus('IDLE');
    readonly form: FormGroup<FileUploadForm>;
    private _startValue: undefined;
    skipSaveConfirmation = true;
    redirectAfterSavePath = ["../sign"]

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
        });
    }
    submitForm() {
        if (this.form.invalid) {
            // this.notification.show({ message: 'text.other.pleaseReview' });
            return;
        }
        // this.save();
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
