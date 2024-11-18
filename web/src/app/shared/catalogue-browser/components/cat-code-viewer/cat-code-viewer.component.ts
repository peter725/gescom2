import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { TswButtonBase } from '@tulsa/app/shared/components/buttons/button-base';
import { Observable } from 'rxjs';
import { CatalogueBrowserService } from '../../catalogue-browser.service';
import { GeneratedCode } from '../../models';


@Component({
  selector: 'tsw-cat-code-viewer',
  templateUrl: './cat-code-viewer.component.html',
  styles: [],
})
export class CatCodeViewerComponent implements OnInit {

  @Input() showActions = true;

  @ViewChild('copyBtn') copyBtn: TswButtonBase | undefined;
  @ViewChild('copyDescBtn') copyDescBtn: TswButtonBase | undefined;

  generatedCode$: Observable<GeneratedCode>;

  constructor(
    private catalogueService: CatalogueBrowserService
  ) {
    this.generatedCode$ = catalogueService.generatedCode$;
  }

  ngOnInit() {
    this.catalogueService.clearCode();
  }

  clearBrowser() {
    this.catalogueService.clearCode();
  }

  copyCode() {
    this.catalogueService.copyValue('code');
    if (this.copyBtn) this.copyBtn.state = 'SUCCESS'
  }

  copyDescription() {
    this.catalogueService.copyValue('desc');
    if (this.copyDescBtn) this.copyDescBtn.state = 'SUCCESS'
  }
}
