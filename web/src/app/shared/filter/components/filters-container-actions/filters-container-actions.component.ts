import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'tsw-filters-container-actions',
  templateUrl: './filters-container-actions.component.html',
  styleUrls: ['./filters-container-actions.component.scss'],
})
export class FiltersContainerActionsComponent {

  @Output() searchEv = new EventEmitter<void>();
  @Output() resetEv = new EventEmitter<void>();

}
