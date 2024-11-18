// import { ResourceAccessKey } from '@libs/security';

// import Any = jasmine.Any;

export type MenuItem = {
  text: string;
  // requireAccess?: ResourceAccessKey;
  requireAccess?: string;
  requireScope?: string;
  path?: string;
  icon?: string;
  expanded?: boolean;
  children?: MenuItem[];
};

export enum SidebarMode {
  FULL= 'full',
  SHORT= 'short',
  OFF= 'off',
}
