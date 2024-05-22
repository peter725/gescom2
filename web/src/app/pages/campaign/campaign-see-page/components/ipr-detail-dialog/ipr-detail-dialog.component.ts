import { Component, Inject, OnInit } from '@angular/core';
import { Protocol, ProtocolDetailForm, Question, QuestionIpr } from '@libs/sdk/protocol';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { TranslateModule } from '@ngx-translate/core';
import { Campaign, CampaignForm } from '@libs/sdk/campaign';
import { MatGridListModule } from '@angular/material/grid-list';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'tsw-ipr-detail',
  templateUrl: './ipr-detail-dialog.component.html',
  styleUrls: ['./ipr-detail-dialog.component.scss'],
  standalone: true,
  imports: [
    MatDialogModule,
    MatButtonModule,
    TranslateModule,
    MatGridListModule,
    CommonModule
  ]
})
export class IprDetailComponent implements OnInit {

  preguntas: QuestionIpr [] | undefined;
  colorGris: any = '#e3e3e3';


  constructor(private dialogRef: MatDialogRef<IprDetailComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,) { }

  ngOnInit(): void {
    console.log('datos dialog detalle',this.data);

    this.preguntas = this.data.iprQuestionDTOList;

    console.log('preguntas', this.preguntas);

    // this.protocol = this.data.protocol;
    // this.campaign = this.data.campaign;
    // this.preguntas = this.data.protocol.question
    // if (Array.isArray(this.campaign?.['participants'])) {
    //   this.participantes = this.campaign?.['participants']?.map((item: any) => item.name).join(', ');
    // } else {
    //   this.participantes = '';
    // }
  }

  close(): void {
    this.dialogRef.close();
  }

}
