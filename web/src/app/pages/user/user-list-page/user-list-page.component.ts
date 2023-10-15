import { Component, OnInit } from '@angular/core';
import { FilterService } from '@base/shared/filter';
import { BaseListPageComponent } from '@base/shared/pages/list';
import { SampleContextService } from '@base/shared/sample/sample-context.service';
import { CrudImplService, RequestConfig } from '@libs/crud-api';
import { User } from '@libs/sdk/user';
import { firstValueFrom, Observable } from 'rxjs';
import { takeUntil, skip } from 'rxjs/operators';


@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-user-list-page',
  templateUrl: './user-list-page.component.html',
  styleUrls: ['./user-list-page.component.scss'],
})
export class UserListPageComponent extends BaseListPageComponent<User> implements OnInit {

  readonly resourceName = 'users';

  constructor(
    crudService: CrudImplService<User>,
    filterService: FilterService,
    private sampleCtx: SampleContextService,
  ) {
    super(crudService, filterService);
  }

  override async ngOnInit() {
    await super.ngOnInit();
    // this.monitorCtxChanges();
  }

  protected override async getRequestConfig(): Promise<RequestConfig> {
    const config = await super.getRequestConfig();
    const scope = (await firstValueFrom(this.sampleCtx.scope$)).scopeCode;
    config.queryParams = {
      ...config.queryParams,
      scope,
    };
    return config;
  }

  protected getColumns(): string[] {
    return ['select', 'name', 'surnames', 'phone', 'email', 'profiles', 'actions'];
  }

  private monitorCtxChanges() {
    this.sampleCtx.scope$.pipe(takeUntil(this.destroyed$), skip(1)).subscribe(() => this.reloadData());
  }

}
