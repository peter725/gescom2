import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FORM_STATUS } from '@base/shared/components/form';
import { NotificationService } from '@base/shared/notification';
import { CustomSelectComponent } from '@base/shared/select';
import { ComponentStatus, ControlsOf, FormMapper } from '@libs/commons';
import { CrudImplService, RequestConfig, RequestParams } from '@libs/crud-api';
import { ModelStates } from '@libs/sdk/common';
import { Entity } from '@libs/sdk/entity';
import { createScopeId, Scope, ScopeForm } from '@libs/sdk/scope';
import { firstValueFrom, Observable } from 'rxjs';

@Component({
    // eslint-disable-next-line @angular-eslint/component-selector
    selector: 'tsw-user-edit-scope-form',
    templateUrl: './user-edit-scope-form.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ],
})
export class UserEditScopeFormComponent implements OnInit {
    @ViewChild('entitySelect') entitySelectRef: CustomSelectComponent<Entity> | undefined;

    readonly resourceName = 'scopes';
    readonly resourceId: string;
    readonly sectionTitle: string;

    readonly entitySelectQuery: RequestParams = {
        state: [ModelStates.ON],
    };

    form: FormGroup<ControlsOf<ScopeForm>>;

    private activeOperation: Observable<Scope> | undefined;

    constructor(
        private fb: FormBuilder,
        private ref: MatDialogRef<Partial<ScopeForm>>,
        private crudService: CrudImplService<Scope, string>,
        private notification: NotificationService,
        private mapper: FormMapper<Scope, ScopeForm>,
        @Inject(MAT_DIALOG_DATA) private data: Partial<ScopeForm>,
        @Inject(FORM_STATUS) public status: ComponentStatus,
    ) {
        this.form = this.buildForm();
        this.resourceId = createScopeId(data);
        this.sectionTitle = this.isNew ? 'pages.scope.add' : 'pages.scope.edit';
    }

    ngOnInit(): void {
        this.loadData();

    }

    async submitForm() {
        if (this.form.invalid) {
            this.notification.show({ message: 'text.other.pleaseReview' });
            return;
        }

        try {
            this.status.status = 'PROCESS';

            const raw = { ...this.form.getRawValue() } as Scope;

            const payload = {
                user: raw.user,
                entity: { id: raw.entity.id },
            } as Scope;

            console.log(payload)

            const config: RequestConfig = { resourceName: this.resourceName };
            if (this.activeOperation) {
                this.activeOperation = undefined;
            }

            if (this.isNew) {
                this.activeOperation = this.crudService.create(payload, config);
            } else {
                const id = this.resourceId;
                config.pathParams = { id };
                this.activeOperation = this.crudService.update(id, payload, config);
            }

            const result = await firstValueFrom(this.activeOperation);

            this.form.markAsPristine();
            this.form.markAsPristine();

            this.activeOperation = undefined;
            this.status.status = 'IDLE';

            this.closeDialog(result);
        } catch (e) {
            this.status.status = 'ERROR';
        }
    }

    resetForm() {
        this.form.patchValue(this.data);
    }

    closeDialog(result?: Scope) {
        this.ref.close(result);
    }

    get isNew() {
        return this.resourceId.includes('0');
    }

    private loadData() {
        this.form.patchValue(this.data, { emitEvent: false });

    }

    private buildForm() {
        return this.fb.group<ControlsOf<ScopeForm>>({
            user: this.fb.control(null, [Validators.required]),
            entity: this.fb.control(null, [Validators.required])
        });
    }
}
