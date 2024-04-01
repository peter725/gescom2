import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import {Entity, EntityForm} from "@libs/sdk/entity";

@Component({
    selector: 'tsw-entity-edit-page',
    templateUrl: './entity-edit-page.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})
export class EntityEditPageComponent extends EditPageBaseComponent<Entity, EntityForm> {

    readonly resourceName = 'entities';

    protected override _createResourceTitle = 'pages.entity.add';
    protected override _editResourceTitle = 'pages.entity.edit';

    protected buildForm(): FormGroup<ControlsOf<EntityForm>> {
        return this.fb.group<ControlsOf<EntityForm>>({
            id: this.fb.control(null),
            languageId: this.fb.control(1),
            name: this.fb.control('', [Validators.required]),
            description: this.fb.control('', []),

            parent: this.fb.control(null, [Validators.required]),
            code: this.fb.control('', [Validators.required]),
            postalCode: this.fb.control('', []),
            address: this.fb.control('', []),
            contactPerson: this.fb.control('', []),
            phone: this.fb.control('', []),
            email: this.fb.control('', [Validators.email]),
            entityType: this.fb.control(null, [Validators.required])
        });
    }

    override mapFormToModel(src: EntityForm) {
        if(src.parent) {
            src.parent = { id: src.parent.id };
        }
        return super.mapFormToModel(src);
    }

}



