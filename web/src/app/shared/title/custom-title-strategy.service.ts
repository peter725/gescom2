import { Injectable } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { RouterStateSnapshot, TitleStrategy } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { appName } from '@base/config/app';
import { map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CustomTitleStrategyService extends TitleStrategy {

  constructor(
    private title: Title,
    private translate: TranslateService,
  ) {
    super();
  }

  updateTitle(snapshot: RouterStateSnapshot): void {
    const rawTitle = this.buildTitle(snapshot);
    if (rawTitle) {
      this.translate.get(rawTitle).pipe(
        map(title => `${ appName } - ${ title }`),
      ).subscribe({
        next: title => this.title.setTitle(title),
        error: err => {
          console.error(err);
          this.title.setTitle(`Title loading error!`);
        }
      });
    }
  }

}
