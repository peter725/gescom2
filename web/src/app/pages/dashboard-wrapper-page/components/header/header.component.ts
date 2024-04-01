import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {dgc, government, ministry} from '@base/config/app';
// import { SampleContextService } from '@tulsa/app/shared/sample/sample-context.services';
// import { TulsaAuthSubject } from '@tulsa/app/shared/security';
import { CrudImplService, RequestConfig, SortBuilder, SortDirection } from '@libs/crud-api';
import { ModelStates } from '@libs/sdk/common';
// import { TulsaLanguage } from '@libs/sdk/language';
// import { AuthContextService } from '@tulsa/libs/security';
import { Observable } from 'rxjs';
import { AuthStorage } from '@base/shared/security/auth-storage';

@Component({
  selector: 'tsw-header, nav[tsw-header]',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {

  logos = [dgc];
  nombreCompleto: string = '';

  constructor() {}

  ngOnInit(): void {
    const userSession = AuthStorage.getUserSession();
    this.nombreCompleto = userSession.firstName + " " + userSession.firstSurname;
  }

}
