import {Injectable, NgZone} from '@angular/core';
import {TAB_PANEL_ITEMS} from '@base/shared/components/tabpanel/items';
import {PreferencesService} from '@base/shared/preferences';
import {BehaviorSubject} from 'rxjs';
import {MenuItem} from '../sidebar/model';


@Injectable({ providedIn: 'root' })
export class TabPanelService {


  private readonly menuItems = new BehaviorSubject<MenuItem[]>([]);

  constructor(
    private preferenceService: PreferencesService,
    private zone: NgZone
  ) {
    this.monitorAuthContext();
  }

  get items$() {
    return this.menuItems.asObservable();
  }

  private  monitorAuthContext() {
    this.updateMenuItems()
  }

  private  updateMenuItems() {
    this.menuItems.next(TAB_PANEL_ITEMS);
  }


}
