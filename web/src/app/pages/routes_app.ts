import {Routes} from '@angular/router';
import { USER_PAGE_ROUTES } from './user/routes';
import {APPROACH_PAGE_ROUTES} from "@base/pages/approach/routes";
import { CAMPAIGN_PAGE_ROUTES } from './campaign/routes';
import { ROLE_PAGE_ROUTES } from './role/routes';


export const dashboardRoutes: Routes = [
  {
    path: 'inicio',
    loadChildren: () => import('./home-page/home-page.module').then(m => m.HomePageModule),
  },
  {
    path: 'usuarios',
    data: {
      requireAccess: 'user',
      requireScope: 'rr'
    },
    children: USER_PAGE_ROUTES,
  },
  {
    path: 'campanas',
    data: {
      requireAccess: 'campaign',
      requireScope: 'rr'
    },
    children: CAMPAIGN_PAGE_ROUTES,
  },
  {
    path: 'propuestas',
    data: {
      requireAccess: 'approach',
      requireScope: 'rr'
    },
    children: APPROACH_PAGE_ROUTES,
  },
  {
    path: 'role',
    data: {
      requireAccess: 'role',
      requireScope: 'rr'
    },
    children: ROLE_PAGE_ROUTES,
  },
  // {
  //   path: 'profile',
  //   data: {
  //     requireAccess: 'profile',
  //     requireScope: 'rr'
  //   },
  //   children: PROFILE_PAGE_ROUTES,
  // },
  // {
  //   path: 'module',
  //   data: {
  //     // requireAccess: 'module'
  //   },
  //   children: MODULE_PAGE_ROUTES,
  // },
  // {
  //   path: 'entity',
  //   data: {
  //     requireAccess: 'entity',
  //     requireScope: 'rr'
  //   },
  //   children: ENTITY_PAGE_ROUTES,
  // },
  // {
  //   path: 'protocol',
  //   data: {
  //     // requireAccess: 'RFieldMod'
  //   },
  //   children: PROTOCOL_PAGE_ROUTES,
  // },
  // {
  //   path: 'infracciones',
  //   data: {
  //     // requireAccess: 'RFieldMod'
  //   },
  //   children: INFRINGEMENT_PAGE_ROUTES,
  // },


];
