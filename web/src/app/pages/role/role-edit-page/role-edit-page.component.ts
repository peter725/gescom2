import { Component } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
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
}
