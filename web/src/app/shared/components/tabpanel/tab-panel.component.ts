import {Component, OnDestroy, OnInit} from '@angular/core';
import {MenuItem} from '@base/shared/components/sidebar/model';
import {Observable, ReplaySubject, takeUntil} from 'rxjs';
import {TabPanelService} from './tab-panel.service';
import {map} from "rxjs/operators";
import {ActivatedRoute} from "@angular/router";


@Component({
    selector: 'tsw-tab-panel',
    templateUrl: './tab-panel.component.html',
    styleUrls: ['./tab-panel.component.scss']
})
export class TabPanelComponent implements OnInit, OnDestroy {

    sidebarMode: string[] = [];
    items: MenuItem[] = [];

    private readonly destroyed$ = new ReplaySubject(1);
    pathId: number = 0;

    constructor(private tabPanelService: TabPanelService,
                private route: ActivatedRoute,) {

    }

    ngOnInit(): void {
        this.tabPanelService.items$.pipe(takeUntil(this.destroyed$)).subscribe(value => this.items = value);
        this.loadResourceId().pipe(takeUntil(this.destroyed$)).subscribe(value => this.pathId = value)

    }

    ngOnDestroy(): void {
        this.destroyed$.next(true);
    }


    private loadResourceId(): Observable<any> {
        return this.route.paramMap.pipe(
            map(v => v.get('id') || '0'),
        );
    }
}
