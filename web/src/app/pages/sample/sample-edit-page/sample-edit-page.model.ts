import { FormControl } from '@angular/forms';
import { TulsaModule } from '@tulsa/libs/sdk/module';
import { TulsaSampleSeason } from '@tulsa/libs/sdk/sample-season';
import { VTulsaSampleTemplate } from '@tulsa/libs/sdk/sample-template';
import { TulsaScopeView } from '@tulsa/libs/sdk/scope';
import { Observable } from 'rxjs';

export type ConfigForm = {
  module: FormControl<TulsaModule | null>;
  season: FormControl<TulsaSampleSeason | null>;
  scope: FormControl<TulsaScopeView | null>;
  useTemplate: FormControl<boolean>;
  template: FormControl<VTulsaSampleTemplate | null>;
};

export type ConfigData = {
  modules$: Observable<TulsaModule[]>,
  scopes$: Observable<TulsaScopeView[]>,
  seasons$: Observable<TulsaSampleSeason[]>,
  templates$: Observable<VTulsaSampleTemplate[]>,
};
