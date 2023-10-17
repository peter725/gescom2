import { _DisposeViewRepeaterStrategy, _VIEW_REPEATER_STRATEGY } from '@angular/cdk/collections';
import {
  _COALESCED_STYLE_SCHEDULER,
  _CoalescedStyleScheduler,
  CDK_TABLE,
  CDK_TABLE_TEMPLATE,
  CdkTable,
  STICKY_POSITIONING_LISTENER
} from '@angular/cdk/table';
import { ChangeDetectionStrategy, Component, ViewEncapsulation } from '@angular/core';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'tsw-table, table[tsw-table]',
  exportAs: 'tswTable',
  templateUrl: './tsw-table.component.html',
  styleUrls: ['./tsw-table.component.scss'],
  providers: [
    { provide: CdkTable, useExisting: TswTableComponent },
    { provide: CDK_TABLE, useExisting: TswTableComponent },
    { provide: _COALESCED_STYLE_SCHEDULER, useClass: _CoalescedStyleScheduler },
    // Abstract the view repeater strategy to a directive API so this code is only included in the build if used.
    { provide: _VIEW_REPEATER_STRATEGY, useClass: _DisposeViewRepeaterStrategy },
    // Prevent nested tables from seeing this table's StickyPositioningListener.
    { provide: STICKY_POSITIONING_LISTENER, useValue: null },
  ],
  encapsulation: ViewEncapsulation.None,
  // See note on CdkTable for explanation on why this uses the default change detection strategy.
  // tslint:disable-next-line:validate-decorators
  changeDetection: ChangeDetectionStrategy.Default,
})
export class TswTableComponent<T> extends MatTable<T> {

}
