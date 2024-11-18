import { SidebarMode } from '@base/shared/components/sidebar/model';


export interface UserPreferences {
  sidebar: SidebarMode;
  langCode?: string;
  moduleId?: number;
  scopeCode?: string;
  seasonId?: number;
}
