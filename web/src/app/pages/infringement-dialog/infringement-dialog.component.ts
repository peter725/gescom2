import { Component } from '@angular/core';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { InfringementListPageModule } from '@base/pages/infringement';
import { Infringement } from '@libs/sdk/infringement';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';

let infraccion : Infringement | null;

@Component({
  selector: 'app-infringement-dialog',
  templateUrl: './infringement-dialog.component.html',
  standalone: true,
  imports: [
    InfringementListPageModule,
    MatDialogModule,
    MatButtonModule,
    TranslateModule
  ]
})
export class InfringementDialogComponent {

  dataSource = infraccion ? [infraccion] : [];
  form: FormGroup | undefined;


  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<InfringementDialogComponent>) {
    this.form = this.fb.group({
      infringement: this.fb.control(null),
      code: this.fb.control(null),
    });
  }

  handleSelectionChange(event: {row: any, selected: boolean}) {
    if (event.selected) {
      // Añadir el elemento si no existe
      if (!this.dataSource.some(item => item.id === event.row.id)) {
        this.dataSource.push(event.row);
      }
    } else {
      // Remover el elemento si ya no está seleccionado
      this.dataSource = this.dataSource.filter(item => item.id !== event.row.id);
    }
  }


  closeDialog() {
    console.log(this.dataSource);
    this.dialogRef.close(this.dataSource);
  }
}