import { Component, Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ColumnSource } from '@base/shared/collections';
import { CrudImplService, Page, RequestConfig } from '@libs/crud-api';
import { ScopeForm, ScopeView, createScopeId } from '@libs/sdk/scope';
import { User } from '@libs/sdk/user';
import { filter } from 'rxjs';
import { UserEditScopeFormComponent } from '../user-edit-scope-form/user-edit-scope-form.component';

@Component({
    // eslint-disable-next-line @angular-eslint/component-selector
    selector: 'tsw-user-edit-scope-list',
    templateUrl: './user-edit-scope-list.component.html',
    styles: [],
})
export class UserEditScopeListComponent {

    readonly resourceName = 'scopes';
    readonly source = new MatTableDataSource<ScopeView>([]);

    private activeUser: User | undefined;
    private paginator = Page.unpaged().event;

    readonly columns = new ColumnSource(['entity', 'actions']);

    constructor(
        private crudService: CrudImplService<ScopeView>,
        private dialog: MatDialog,
    ) {
    }

    @Input()
    set user(user: User | undefined) {
        if (user?.id !== this.user?.id) {
            this.activeUser = user;
            this.loadData();
        }
    }

    editScope(scope?: ScopeView | undefined) {
        let data: Partial<ScopeForm>;
        if (!scope) {
            data = { user: this.activeUser?.id || 0 };
        } else {
            data = {
                user: scope.user,
                entity: { id: scope.entity, name: scope.entityName },
            };
        }

        const ref = this.dialog.open(UserEditScopeFormComponent, {
            data,
            panelClass: 'p-0'
        });
        ref.afterClosed().pipe(filter(result => !!result)).subscribe(() => this.loadData());
    }

    private loadData() {
        if (!this.activeUser) return;
        const config: RequestConfig = {
            resourceName: this.resourceName,
            queryParams: { userId: this.activeUser.id },
        };
        this.crudService.findAll(config).subscribe(
            page => this.afterDataLoaded(page)
        );
    }

    private afterDataLoaded(page: Page<ScopeView>) {
        this.source.data = [...page.content].map(v => {
            v.id = createScopeId(v);
            return v;
        });
    }
}
