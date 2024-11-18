import { Component } from '@angular/core';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { InfringementListPageModule } from '@base/pages/infringement';
import { Infringement } from '@libs/sdk/infringement';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';
import { ProductsListComponent } from './products-list.component';
import { ProductService } from '@libs/sdk/productService';
import { ProductsListModule } from './products-list.module';

let producto : ProductService | null;

@Component({
  selector: 'app-products-dialog',
  templateUrl: './products-dialog.component.html',
  standalone: true,
  imports: [
    ProductsListModule,
    MatDialogModule,
    MatButtonModule,
    TranslateModule
  ]
})
export class ProductsDialogComponent {

  dataSource = producto ? [producto] : [];
  form: FormGroup | undefined;


  constructor(private fb: FormBuilder, public dialogRef: MatDialogRef<ProductsDialogComponent>) {
    this.form = this.fb.group({
      name: this.fb.control(null),
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