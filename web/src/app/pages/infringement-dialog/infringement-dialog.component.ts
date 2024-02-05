import { Component } from '@angular/core';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { InfringementListPageModule } from '@base/pages/infringement';

@Component({
  selector: 'app-infringement-dialog',
  templateUrl: './infringement-dialog.component.html',
  standalone: true,
  imports: [
    InfringementListPageModule,
    MatDialogModule
  ]
})
export class InfringementDialogComponent {


}