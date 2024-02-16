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
  preguntas: Question [] | undefined;
  colorGris: any = '#e3e3e3';

  /*protocolDetail: ProtocolDetailForm = {
    // Tus datos aquí
    CampaignName: 'Campaña de ejemplo',
    year: '2024',
    typeCampaign: 'Tipo 1',
    ambit: 'Nacional',
    responsible: 'Juan Pérez',
    participants: '100',
    codeCPA: 'CPA123',
    protocolName: 'Nombre del Protocolo',
    questions: [
      { question: 'Cabecera 1', infringement: '' },
      { question: 'Pregunta 1', infringement: 'Infracción 1' },
      // Agrega más preguntas según sea necesario
    ]
  };*/

  constructor(private dialogRef: MatDialogRef<ProtocolDetailComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,) { }

  ngOnInit(): void {
    this.protocol = this.data.protocol;
    this.campaign = this.data.campaign;
    this.preguntas = this.data.protocol.question
    if (Array.isArray(this.campaign?.['participants'])) {
      this.participantes = this.campaign?.['participants']?.map((item: any) => item.name).join(', ');
    } else {
      this.participantes = '';
    }
  }

  close(): void {
    this.dialogRef.close();
  }

}
