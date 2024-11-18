import { Component } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import { Permission } from '@libs/sdk/permission';
import { Role, RoleForm, RoleModule } from '@libs/sdk/role';

@Component({
  selector: 'tsw-role-edit-page',
  templateUrl: './role-edit-page.component.html',
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') }
  ]
})
export class RoleEditPageComponent extends EditPageBaseComponent<Role, RoleForm> {

  readonly resourceName = 'roles';

  protected override _createResourceTitle = 'pages.role.add';
  protected override _editResourceTitle = 'pages.role.edit';

  protected buildForm(): FormGroup {
    return this.fb.group({
      id: this.fb.control(null),
      name: this.fb.control(null, [Validators.required, Validators.maxLength(100)]),
      modules: this.fb.array([this.crearFilaModule(1)], [Validators.required, Validators.min(1)])
    });
  }

  protected override async afterLoadDataSuccess(result: any) {
    super.afterLoadDataSuccess(result);

    const modules = this.form.get('modules') as unknown as FormArray; 
    if (result && result.modules) {
      result.modules.forEach((q: any, index: number) => {
        if(index > 0) modules.push(this.loadRowModule(q.module, q.permissions));
      });
    }
  }

  loadRowModule(module: string, permissions: Permission[]): FormGroup {
    return this.fb.group({
      module: this.fb.control(module, [Validators.required]),
      permissions: this.fb.control(permissions, [Validators.required, Validators.min(1)]),
    });
  }

  get modules() {
    return this.form.get('modules') as unknown as FormArray;
  }

  crearFilaModule(orden: number): FormGroup {
    console.log('crearFila', orden);
    return this.fb.group({
      module: this.fb.control(null, [Validators.required]),
      permissions: this.fb.control([], [Validators.required, Validators.min(1)]),
    });
  }

  agregarFilaModule() {
    const moduleControl = this.form.get('modules') as unknown as FormArray;
    if (moduleControl.invalid) {
      moduleControl.markAllAsTouched();
      return;
    }
    const nuevoOrden = moduleControl.length + 1;
    moduleControl.push(this.crearFilaModule(nuevoOrden));
  }

  eliminarFilaModule(index: number) {
    // Elimina la fila en el índice dado
    this.modules.removeAt(index);

    // Recorre todas las filas restantes para actualizar el campo 'orden'
    this.modules.controls.forEach((modules, i) => {
      console.log('modules', modules);
      modules.get('order')?.setValue(i + 1);
    });
  }

  errorsModuleModule(index: number) {
    return (this.modules.controls[index] as FormGroup).controls['module'].errors;
  }

  errorsModulePermissions(index: number) {
    return (this.modules.controls[index] as FormGroup).controls['permissions'].errors;
  }

  override resetForm(): void {
    super.resetForm();

    (this.form.controls.modules as unknown as FormArray).clear();
    (this.form.controls.modules as unknown as FormArray).push(this.crearFilaModule(1));
    this.afterLoadDataSuccess(this.startValue);
  }
}
