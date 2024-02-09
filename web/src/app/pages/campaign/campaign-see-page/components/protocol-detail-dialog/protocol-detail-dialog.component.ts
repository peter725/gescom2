import { Component, OnInit } from '@angular/core';
import { ProtocolDetailForm } from '@libs/sdk/protocol';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'tsw-protocol-detail',
  templateUrl: './protocol-detail-dialog.component.html',
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    TranslateModule
  ]
})
export class ProtocolDetailComponent implements OnInit {

  protocolDetail: ProtocolDetailForm = {
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
  };

  constructor() { }

  ngOnInit(): void {
  }

}
