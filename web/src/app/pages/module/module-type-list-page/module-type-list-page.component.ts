import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { CrudImplService } from '@libs/crud-api';
import { AuthContextService } from '@libs/security';
import { Observable } from 'rxjs';
import {AppAuthSubject} from "@base/shared/security/auth-subject";

@Component({
    selector: 'tsw-module-type-list-page',
    templateUrl: './module-type-list-page.component.html',
    styles: [],
})
export class ModuleTypeListPageComponent extends BaseListPageComponent implements OnInit {

    readonly resourceName = 'moduleTypes';

    subject$: Observable<AppAuthSubject>;

    constructor(
        crudService: CrudImplService<any, number>,
        filterService: FilterService,
        private authContext: AuthContextService<AppAuthSubject>,
    ) {
        super(crudService, filterService);
        this.subject$ = authContext.get();
    }

    protected getColumns(): string[] {
        return ['select', 'element', 'actions'];
    }
}
