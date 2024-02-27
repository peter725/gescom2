import {Component, OnInit} from '@angular/core';
import {Event, NavigationError, RouteConfigLoadEnd, RouteConfigLoadStart, Router} from '@angular/router';
import {SidebarMode, SidebarService} from "@base/shared/components/sidebar";
import { AuthStorage } from '@base/shared/security/auth-storage';
import {ReplaySubject, takeUntil} from "rxjs";

@Component({
  selector: 'tsw-dashboard-wrapper-page',
  templateUrl: './dashboard-wrapper-page.component.html',
  styleUrls: ['./dashboard-wrapper-page.component.scss'],
})
export class DashboardWrapperPageComponent implements OnInit {

  sidebarMode: string[] = [SidebarMode.FULL];
  routeConfigLoading = false;
  routeConfigError: string | undefined;

  private readonly destroyed$ = new ReplaySubject<boolean>(1);

  constructor(
    private router: Router,
    private sidebarService: SidebarService,
  ) {
  }

  ngOnInit(): void {
    this.listenToRouterEvents();
    this.syncSidebarMode();
    this.routeConfigLoading = false

  }

  ngOnDestroy(): void {
    this.destroyed$.next(true);
  }

  private syncSidebarMode() {
    this.sidebarService.mode$.pipe(takeUntil(this.destroyed$)).subscribe(value => this.sidebarMode = [value]);
  }

  private listenToRouterEvents(): void {
    this.router.events.pipe(takeUntil(this.destroyed$)).subscribe({
      next: ev => this.onRouterEvent(ev),
      error: err => this.onRouterEventErr(err),
    });
  }

  private onRouterEvent(ev: Event) {

    this.routeConfigError = undefined;
    if (ev instanceof RouteConfigLoadStart) {
      this.routeConfigLoading = true;
    } else if (ev instanceof RouteConfigLoadEnd) {
      this.routeConfigLoading = false;
    } else if (ev instanceof NavigationError) {
      this.onRouterEventErr(ev.error);
    }
  }

  private onRouterEventErr(err: any) {
    this.routeConfigError = err?.toString ? err.toString() : 'Unknown load error';
  }
}
