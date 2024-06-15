import { Component, Inject, OnInit } from '@angular/core';
import { Protocol, ProtocolDetailForm, Question } from '@libs/sdk/protocol';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';
import { Campaign, CampaignForm } from '@libs/sdk/campaign';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'tsw-protocol-detail',
  templateUrl: './protocol-detail-dialog.component.html',
  styleUrls: ['./protocol-detail-dialog.component.scss'],
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    TranslateModule,
    MatGridListModule,
    CommonModule
  ]
})
export class ProtocolDetailComponent implements OnInit {

  protocol: Protocol | undefined;
  campaign: CampaignForm | undefined;
  participantes: String | undefined;
  preguntasProtocol: Question [] | undefined;
  colorGris: any = '#e3e3e3';


  constructor(private dialogRef: MatDialogRef<ProtocolDetailComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,) { }

  ngOnInit(): void {
    this.protocol = this.data.protocol;
    this.campaign = this.data.campaign;
    this.preguntasProtocol = this.data.protocol.question
    if (Array.isArray(this.campaign?.['participants'])) {
      this.participantes = this.campaign?.['participants']?.map((item: any) => item.name).join(', ');
    } else {
      this.participantes = '';
    }
    console.log('dialog preguntas', this.preguntasProtocol)
  }

  close(): void {
    this.dialogRef.close();
  }

}
