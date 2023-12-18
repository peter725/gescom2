import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { AppContextService } from '@base/shared/app-context';
import { AppError, ComponentStatus, ErrorDetails } from '@libs/commons';
import { CrudImplService } from '@libs/crud-api';
import { B64EncodedFile, filesToB64 } from '@libs/file';
import { Module } from '@libs/sdk/module';
import { SampleSeason } from '@libs/sdk/sample-season';
import { ScopeView } from '@libs/sdk/scope';
import { combineLatest, firstValueFrom } from 'rxjs';


type FileUploadForm = {
  module: FormControl<Module | null>,
  season: FormControl<SampleSeason | null>,
  scope: FormControl<ScopeView | null>,
  file: FormControl<B64EncodedFile | null>,
};
const SUCCESS_CLOSE_TIMEOUT = 2000;
const ERROR_CLOSE_TIMEOUT = 4000;

@Component({
  // eslint-disable-next-line @angular-eslint/component-selector
  selector: 'tsw-sample-uploader',
  templateUrl: './sample-uploader.component.html',
  styleUrls: ['./sample-uploader.component.scss'],
})
export class SampleUploaderComponent {
  readonly process = new ComponentStatus('IDLE');
  readonly form: FormGroup<FileUploadForm>;

  readonly pickerOpts = {
    type: 'file',
    accept: 'text/xml'
  };

  uploadError: ErrorDetails | undefined;

  private autoCloseTimeoutId: any | undefined;

  constructor(
    private ref: MatDialogRef<unknown>,
    private fb: FormBuilder,
    private crudService: CrudImplService,
    public ctx: AppContextService,
  ) {
    this.form = this.buildForm();
    this.initializeData();
  }

  async uploadSample() {
    try {
      this.process.status = 'PROCESS';
      this.form.disable();

      // Implementación de la carga de datos
      // Mostrar los mensajes necesarios
      // Cerrar la modal automáticamente
      const copy = {...this.form.value};
      const payload = {
        samples: copy.file?.data,
        module: copy.module,
        season: copy.season,
        scopeCode: copy.scope?.scopeCode,
      };
      const request = this.crudService.create<void>(payload, {
        resourceName: 'sampleFileUpload',
      });
      await firstValueFrom(request);

      this.process.status = 'DONE'
      this.form.enable();
      this.autoCloseTimeoutId = setTimeout(
        () => this.closeDialog(true),
        SUCCESS_CLOSE_TIMEOUT
      );
    } catch (e) {
      this.process.status = 'ERROR';
      this.form.enable();
      this.uploadError = AppError.parse(e);
    }
  }

  async cancelProcess() {
    this.process.status = 'IDLE';
    // Cancelar el proceso de guardar si existe
    this.closeDialog(false);
  }

  closeDialog(completed: boolean) {
    if (this.autoCloseTimeoutId) clearTimeout(this.autoCloseTimeoutId);
    this.ref.close(completed);
  }

  async fileDropped(ev: DragEvent) {
    ev.preventDefault();

    const files = ev.dataTransfer?.files;
    if (!files?.length) return;

    const [sampleFile] = await filesToB64(files);
    if (!sampleFile || !this.pickerOpts.accept.includes(sampleFile.type)) return;

    const ctrl = this.form.controls.file;
    ctrl.patchValue(sampleFile);
  }

  dragOverEv(ev: Event) {
    ev.preventDefault();
  }

  private async initializeData() {
    const [module, season, scope] = await firstValueFrom(combineLatest([
      this.ctx.module$,
      this.ctx.season$,
      this.ctx.scope$,
    ]));
    this.form.patchValue({ module, season, scope }, { emitEvent: false });
  }

  private buildForm() {
    return this.fb.group<FileUploadForm>({
      module: this.fb.control(null, [Validators.required]),
      season: this.fb.control(null, [Validators.required]),
      scope: this.fb.control(null, [Validators.required]),
      file: this.fb.control(null, [Validators.required]),
    });
  }
}
