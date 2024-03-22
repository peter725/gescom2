import {Injectable, NgZone} from '@angular/core';
import { SIDEBAR_ITEMS } from '@base/shared/components/sidebar/items';
import { PreferencesService } from '@base/shared/preferences';
// import { AclService, AuthContextService } from '@libs/security';
import { AsyncListFilter } from '@libs/utils/async-list-filter';
import { BehaviorSubject } from 'rxjs';
import { MenuItem, SidebarMode } from './model';
import { AuthContextService } from '@libs/security';


@Injectable({ providedIn: 'root' })
export class SidebarService {

  private readonly currentMode: BehaviorSubject<SidebarMode>;
  private readonly menuItems = new BehaviorSubject<MenuItem[]>([]);

  constructor(
    private preferenceService: PreferencesService,
    private authContext: AuthContextService,
    // private aclService: AclService,
    private zone: NgZone
  ) {
    this.currentMode = new BehaviorSubject<SidebarMode>(preferenceService.current.sidebar);
    this.monitorAuthContext();
  }

  get mode$() {
    return this.currentMode.asObservable();
  }

  get items$() {
    return this.menuItems.asObservable();
  }

  toggle() {
    const current = this.currentMode.value;
    const next = current === SidebarMode.FULL ? SidebarMode.SHORT : SidebarMode.FULL;
    this.currentMode.next(next);
    this.preferenceService.update({ sidebar: next });
  }

  refresh() {
    this.updateMenuItems()
  }

  private  monitorAuthContext() {
    this.authContext.get().subscribe(() => this.updateMenuItems());
    // this.updateMenuItems()
  }

  private async updateMenuItems() {
    if (!this.authContext.instant().isAuthenticated()) {
      return this.menuItems.next([]);
    }
    const newSidebarItems: MenuItem[] = SIDEBAR_ITEMS.map(obj => ({...obj}));
    const items = await this.filterMenuItemAccess(newSidebarItems);
    this.menuItems.next(items);
  }

  private async filterMenuItemAccess(list: MenuItem[]): Promise<MenuItem[]> {
    return await AsyncListFilter.from(list).filter(async item => {
      let canAccess = true;
      if (item.requireAccess) {
        // Comprobamos el requisito de acceso al recurso
        canAccess = await this.authContext.instant().hasModule(item.requireAccess);
        if (canAccess && item.requireScope) {
          canAccess = await this.authContext.instant().hasScope(item.requireAccess, item.requireScope);
        }
      }
      if (canAccess && item.children?.length) {
        item.children = await this.filterMenuItemAccess(item.children);
        // Si no tenemos acceso a ninguno de los hijos, ocultamos el elemento padre
        canAccess = item.children.length > 0;
      }
      return canAccess;
    });
  }
}
