import { SidebarMode } from '@base/shared/components/sidebar/model';
import { UserPreferences } from './model';


export const PREFERENCES_STORAGE_KEY = 'user_pref';

export const DEFAULT_PREFERENCES: UserPreferences = {
  sidebar: SidebarMode.FULL,
};
