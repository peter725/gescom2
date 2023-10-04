import { ModuleWithProviders, NgModule } from '@angular/core';
import { NamedRoutePipe } from './named-route.pipe';
import { APP_ROUTES } from './tokens';
import { NamedRouteSource } from './models';
import { createStorage } from './factories';


@NgModule({
  imports: [],
  declarations: [
    NamedRoutePipe
  ],
  exports: [
    NamedRoutePipe
  ]
})
export class NamedRoutesModule {
  static forRoot(routes: NamedRouteSource[]): ModuleWithProviders<NamedRoutesModule> {
    return {
      ngModule: NamedRoutesModule,
      providers: [
        { provide: APP_ROUTES, useValue: createStorage(routes) }
      ]
    };
  }
}
