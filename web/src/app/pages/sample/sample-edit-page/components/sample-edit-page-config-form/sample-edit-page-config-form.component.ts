import { Component, Inject, Input, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { AppContextService } from '@tulsa/app/shared/app-context';
import { FORM_STATUS } from '@tulsa/app/shared/components/form';
import { compareObjects } from '@tulsa/app/shared/select';
import { ComponentStatus } from '@tulsa/libs/commons';
import { TulsaModule } from '@tulsa/libs/sdk/module';
import { compareScopeView } from '@tulsa/libs/sdk/scope';
import { distinctUntilChanged, filter, firstValueFrom, ReplaySubject, takeUntil } from 'rxjs';
import { ConfigData, ConfigForm } from '../../sample-edit-page.model';
import { downloadLocalFile } from '@tulsa/libs/file/file-downloader';
import { MatDialog } from '@angular/material/dialog';
import { SampleUploaderComponent } from '../../../sample-list-page/components';


@Component({
  selector: 'tsw-sample-edit-page-config-form',
  templateUrl: './sample-edit-page-config-form.component.html',
})
export class SampleEditPageConfigFormComponent implements OnInit, OnDestroy {

  @Input() configForm!: FormGroup<ConfigForm>;

  readonly configData: ConfigData;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  readonly compareObjects = compareObjects;
  readonly compareScopes = compareScopeView;

  constructor(
    private ctx: AppContextService,
    @Inject(FORM_STATUS) public status: ComponentStatus,
    private dialog: MatDialog,
    private sampleCtx: AppContextService,
  ) {
    this.configData = this.getConfigurationValues();
  }

  async ngOnInit() {
    await this.loadConfiguration();
    this.monitorModuleChanges();
  }

  ngOnDestroy() {
    this.destroyed$.next(true);
  }

  async toggleTemplate(useTemplate: boolean) {
    const ctrl = this.configForm.controls.template;
    if (useTemplate) {
      ctrl.setValidators([Validators.required]);
      const [first] = await firstValueFrom(this.ctx.templates$);
      ctrl.patchValue(first);
    } else {
      ctrl.clearValidators();
      ctrl.reset();
    }
  }

  useModule(module: TulsaModule) {
    this.ctx.useModule(module);
  }

  private async loadConfiguration() {
    const module = await firstValueFrom(this.ctx.module$);
    const season = await firstValueFrom(this.ctx.season$);
    const scope = await firstValueFrom(this.ctx.scope$);


    this.configForm.patchValue({
      module,
      season,
      scope,
    });
  }

  async uploadSample() {
    const ref = this.dialog.open(SampleUploaderComponent, {
      panelClass: 'w-1/2',
      disableClose: true,
    });
    const done = await firstValueFrom(ref.afterClosed());
    if (done) {
      //this.reloadData();
    }
  }

  async downloadTemplate() {
    // Esto tendría que ir en algún servicio u obtenerse de otro lugar. De momento nos vale.
    const module = (await firstValueFrom(this.sampleCtx.module$)).code;
    const map: Record<string, string> = {
      'ZOO': 'assets/excels/ZOO_FACT_PREVALENCE_MAN.xls',
      'AMR': 'assets/excels/ZOO_FACT_AMRESBL_MAN.xls',
      'ANY': 'assets/excels/XMLTool_FLAT_CHEMMON_TEST_06.xls',
    };

    let url = map[module];
    if (!url) {
      url = map['ANY'];
    }
    downloadLocalFile(url);
  }

  private getConfigurationValues() {
    return {
      modules$: this.ctx.modules$,
      scopes$: this.ctx.scopes$,
      seasons$: this.ctx.seasons$,
      templates$: this.ctx.templates$,
    };
  }

  private monitorModuleChanges() {
    this.ctx.module$.pipe(
      takeUntil(this.destroyed$),
      distinctUntilChanged(),
      filter(module => module !== this.configForm.value.module),
    ).subscribe(module => {
      this.configForm.controls.module.patchValue(module, { emitEvent: false });
      this.configForm.controls.template.reset();
      this.configForm.controls.season.reset();
    });
  }

}
