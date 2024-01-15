import {Routes} from '@angular/router';
import { USER_PAGE_ROUTES } from './user/routes';
import {APPROACH_PAGE_ROUTES} from "@base/pages/approach/routes";
import { CAMPAIGN_PAGE_ROUTES } from './campaign/routes';
import { INFRACTION_PAGE_ROUTES } from '@base/pages/infraction/routes';
import { PROFILE_PAGE_ROUTES } from '@base/pages/profile/routes';
import { MODULE_PAGE_ROUTES } from '@base/pages/module/routes';
import { ENTITY_PAGE_ROUTES } from '@base/pages/entity/routes';


export const dashboardRoutes: Routes = [
  {
    path: 'inicio',
    loadChildren: () => import('./home-page/home-page.module').then(m => m.HomePageModule),
  },
  {
    path: 'usuarios',
    data: {
      //requireAccess: 'RUser'
    },
    children: USER_PAGE_ROUTES,
  },
  {
    path: 'campanas',
    data: {
      //requireAccess: 'RUser'
    },
    children: CAMPAIGN_PAGE_ROUTES,
  },
  {
    path: 'propuestas',
    data: {
      //requireAccess: 'RUser'
    },
    children: APPROACH_PAGE_ROUTES,
  },
  {
    path: 'infracciones',
    data: {
      //requireAccess: 'RUser'
    },
    children: INFRACTION_PAGE_ROUTES,
  },
  {
    path: 'profile',
    data: {
      // requireAccess: 'RFieldMod'
    },
    children: PROFILE_PAGE_ROUTES,
  },
  {
    path: 'module',
    data: {
      // requireAccess: 'RFieldMod'
    },
    children: MODULE_PAGE_ROUTES,
  },
  {
    path: 'entity',
    data: {
      // requireAccess: 'RFieldMod'
    },
    children: ENTITY_PAGE_ROUTES,
  },
];
