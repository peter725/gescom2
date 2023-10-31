import { FormControl } from '@angular/forms';
import { Module } from '@libs/sdk/module';
import { SampleSeason } from '@libs/sdk/sample-season';
import { VSampleTemplate } from '@libs/sdk/sample-template';
import { ScopeView } from '@libs/sdk/scope';
import { Observable } from 'rxjs';

export type ConfigForm = {
  module: FormControl<Module | null>;
  season: FormControl<SampleSeason | null>;
  scope: FormControl<ScopeView | null>;
  useTemplate: FormControl<boolean>;
  template: FormControl<VSampleTemplate | null>;
};

export type ConfigData = {
  modules$: Observable<Module[]>,
  scopes$: Observable<ScopeView[]>,
  seasons$: Observable<SampleSeason[]>,
  templates$: Observable<VSampleTemplate[]>,
};
