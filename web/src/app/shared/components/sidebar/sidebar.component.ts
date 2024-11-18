import {Component, OnDestroy, OnInit} from '@angular/core';
import {MenuItem} from '@base/shared/components/sidebar/model';
import {ReplaySubject, takeUntil} from 'rxjs';
import {SidebarService} from './sidebar.service';


@Component({
  selector: 'tsw-sidebar, aside[tsw-sidebar]',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit, OnDestroy {

  sidebarMode: string[] = [];
  items: MenuItem[] = [];

  private readonly destroyed$ = new ReplaySubject(1);

  constructor(private sidebarService: SidebarService) {
  }

  ngOnInit(): void {
    this.sidebarService.mode$.pipe(takeUntil(this.destroyed$)).subscribe(value => this.sidebarMode = [value]);
    this.sidebarService.items$.pipe(takeUntil(this.destroyed$)).subscribe(value => this.items = value);
  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  expandItem(index: number) {
    const copy = [...this.items];
    copy.forEach((entry, i) => {
      if (entry.children?.length) {
        entry.expanded = index !== i ? false : !entry.expanded;
      }
    });
    this.items = [...copy];
  }

  onKeydown(event: KeyboardEvent, index: number) {
    if (event.key === 'Enter' || event.key === ' ') {
      this.expandItem(index);
    }
  }

}
