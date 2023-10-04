import { Injectable } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { ConfirmDialogComponent } from './confirm-dialog.component';
import { ConfirmationResp } from './confirmation-response';
import { DEFAULT_PROMPT_ACTIONS } from './constants';
import { ConfirmActionType, ConfirmationPrompt, ConfirmationPromptBtn, ConfirmationPromptSrc } from './models';


@Injectable({ providedIn: 'root' })
export class ConfirmationService {

  constructor(
    private dialog: MatDialog,
    private translate: TranslateService,
  ) {
  }

  confirm(prompt: ConfirmationPromptSrc) {
    const response = new ConfirmationResp();
    const data = this.buildConfirmData(prompt);
    const ref = this.dialog.open(ConfirmDialogComponent, this.buildModalConfig(data));
    ref.afterClosed().subscribe(result => response.resolve(result));
    return response;
  }

  private buildModalConfig(data: ConfirmationPrompt): MatDialogConfig<ConfirmationPrompt> {
    return {
      data,
      minWidth: '15%',
      maxWidth: '35%',
    };
  }

  private buildConfirmData(conf: ConfirmationPromptSrc): ConfirmationPrompt {
    const prompt = this.translate.instant(conf.prompt, conf.promptParams);
    const message = conf.message ? this.translate.instant(conf.message, conf.messageParams) : undefined;
    const buttons = conf.buttons || [...DEFAULT_PROMPT_ACTIONS];
    buttons.forEach(btn => {
      btn.text = this.translate.instant(btn.text);
      btn.type = btn.type || ConfirmActionType.CANCEL;
      btn.color = this.getBtnColor(btn);
    });

    return {
      prompt,
      message,
      buttons,
    };
  }

  private getBtnColor(btn: ConfirmationPromptBtn): ThemePalette {
    if (btn.color) {
      return btn.color;
    }

    // Change to switch if necessary
    return btn.type === ConfirmActionType.REJECT ? 'warn' : undefined;
  }
}
