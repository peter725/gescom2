import { ModuleWithProviders, NgModule } from '@angular/core';
import { CrudOperationStorage } from './operations';
import { CRUD_OPERATIONS } from './tokens';


@NgModule({})
export class CrudModule {
  public static forRoot(operationFactoryFn: () => CrudOperationStorage): ModuleWithProviders<CrudModule> {
    return {
      ngModule: CrudModule,
      providers: [
        { provide: CRUD_OPERATIONS, useFactory: operationFactoryFn }
      ],
    };
  }
}
