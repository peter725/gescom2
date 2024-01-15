import { Component } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { EntityTypeForm, EntityType } from '@libs/sdk/entity-type';

@Component({
    selector: 'tsw-entity-type-edit-page',
    templateUrl: './entity-type-edit-page.component.html',
    providers: [
        { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
    ]
})
export class EntityTypeEditPageComponent extends EditPageBaseComponent<EntityType, EntityTypeForm> {

    readonly resourceName = 'entityTypes';

    protected override _createResourceTitle = 'pages.entityType.add';
    protected override _editResourceTitle = 'pages.entityType.edit';

    protected buildForm(): FormGroup<ControlsOf<EntityTypeForm>> {
        return this.fb.group<ControlsOf<EntityTypeForm>>({
            id: this.fb.control(null),
            languageId: this.fb.control(1),
            name: this.fb.control(null, [Validators.required]),
            description: this.fb.control(null, []),
        });
    }
}
