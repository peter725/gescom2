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

@Component({
  selector: 'tsw-header, nav[tsw-header]',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {

  logos = [dgc];
  // subject$: Observable<TulsaAuthSubject>;

  constructor(
    // public sampleContext: SampleContextService,
    // private crudService: CrudImplService,
    // private translateService: TranslateService,
    // private authContext:object,
  ) {
    // Por defecto este valor es el código ISO del navegador
    // this.subject$ = authContext.get();
  }

}
