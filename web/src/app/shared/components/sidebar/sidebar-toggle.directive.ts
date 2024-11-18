import { Directive, HostListener } from '@angular/core';
import { SidebarService } from './sidebar.service';


@Directive({ selector: '[tswSidebarToggle]' })
export class SidebarToggleDirective {

  constructor(private sidebarService: SidebarService) {
  }

  @HostListener('click', ['$event'])
  onClick() {
    this.sidebarService.toggle();
  }
}
