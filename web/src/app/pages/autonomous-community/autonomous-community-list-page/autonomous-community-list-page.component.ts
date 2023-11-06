import { Component, OnInit } from '@angular/core';
import { ExportFileType } from '@base/shared/export-file';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
//import { AuthSubject } from '@base/shared/security';
import { CrudImplService } from '@libs/crud-api';
import { AuthContextService } from '@libs/security';
import { Observable } from 'rxjs';


@Component({
    selector: 'tsw-autonomous-community-list-page',
    templateUrl: './autonomous-community-list-page.component.html',
})
export class AutonomousCommunityListPageComponent extends BaseListPageComponent implements OnInit {

    readonly resourceName = 'ccaa';
    //subject$: Observable<TulsaAuthSubject>;

    override exportFormats = [ExportFileType.CSV];
    override downloadFileName = 'pages.ccaa.title';

    constructor(
        crudService: CrudImplService<any, number>,
        filterService: FilterService,
        //private authContext: AuthContextService<TulsaAuthSubject>,
    ) {
        super(crudService, filterService);
        //this.subject$ = authContext.get();
    }

    protected getColumns() {
        return ['select', 'name', 'actions'];
    }

}
