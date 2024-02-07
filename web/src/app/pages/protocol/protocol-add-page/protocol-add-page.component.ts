import { Component, Output } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { FORM_STATUS } from '@base/shared/components/form';
import { EditPageBaseComponent } from '@base/shared/pages/edit-page-base.component';
import { ComponentStatus, ControlsOf } from '@libs/commons';
import {MAT_RADIO_DEFAULT_OPTIONS} from "@angular/material/radio";
import { CreateProtocol, Protocol } from '@libs/sdk/protocol';
import { InfringementDialogComponent} from '@base/pages/infringement-dialog/infringement-dialog.component';



@Component({
  selector: 'tsw-protocol-add-page',
  templateUrl: './protocol-add-page.component.html',
  styleUrls: ['./protocol-add-page.component.scss'],
  providers: [
    { provide: FORM_STATUS, useValue: new ComponentStatus('IDLE') },
    { provide: MAT_RADIO_DEFAULT_OPTIONS, useValue: { color: 'black' } }
  ]
})
export class ProtocolAddPageComponent extends EditPageBaseComponent<Protocol, CreateProtocol> {

  readonly resourceName = 'protocol';
  protected override _createResourceTitle = 'pages.protocol.add';

  openDialog(rowIndex: number): void {
    const dialogRef = this.dialog.open(InfringementDialogComponent, {
      width: '75%',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result && result.length > 0) {
        const selectedItem = result[0]; // Si esperas un solo objeto, ajusta según sea necesario
        console.log('selectedItem', selectedItem);
        this.updateFormRowWithSelectedItem(rowIndex, selectedItem);
      }
    });
  }

  updateFormRowWithSelectedItem(rowIndex: number, selectedItem: any) {
    const rows = this.form.get('rows') as unknown as FormArray;
    if (rows.at(rowIndex)) {
      const row = rows.at(rowIndex) as FormGroup;
      row.patchValue({
        // Suponiendo que el campo se llama 'infringement' en tu formulario y 'nombre' en tu objeto seleccionado
        infringement: selectedItem.code,
        // Aquí puedes actualizar otros campos relevantes
      });
      console.log('row', row);
    }
  }

  protected buildForm(): FormGroup {
    const form = this.fb.group({
      name: [null, Validators.required],
      code: [null, ],
      campaign: [null, ],
      rows: this.fb.array([])
    });

    // Usar setTimeout para agregar la primera fila
    setTimeout(() => {
      const rowsControl = form.get('rows') as FormArray;
      rowsControl.push(this.crearFila(1));
    }, 0);

    return form;
  }

  crearFila(orden: number): FormGroup {
    console.log('crearFila', orden);
    return this.fb.group({
      id: null,
      order: [{ value: orden, disabled: true }],
      codeQuestion: null,
      question: null,
      infringement: null,
      response: ['SI'] // Inicializar con 'SI'
    });
  }


  get rows() {
    return this.form.get('rows') as unknown as FormArray;
  }

  agregarFila() {
    const rowsControl = this.form.get('rows') as unknown as FormArray;
    const nuevoOrden = rowsControl.length + 1;
    rowsControl.push(this.crearFila(nuevoOrden));
  }

  eliminarFila(index: number) {
    // Elimina la fila en el índice dado
    this.rows.removeAt(index);

    // Recorre todas las filas restantes para actualizar el campo 'orden'
    this.rows.controls.forEach((control, i) => {
      console.log('control', control);
      control.get('order')?.setValue(i + 1);
    });
  }


  toggleResp(filaIndex: number) {
    const fila = (this.form.get('rows') as unknown as FormArray).at(filaIndex) as FormGroup;
    const currentValue = fila.get('response')?.value;
    fila.get('response')?.setValue(currentValue === 'SI' ? 'NO' : 'SI');
  }
}
