import {Routes} from '@angular/router';
import {ARBITRATION_PAGE_ROUTES} from "@base/pages/arbitration/routes";
import { USER_PAGE_ROUTES } from './user/routes';


export const dashboardRoutes: Routes = [
  {
    path: 'inicio',
    loadChildren: () => import('./home-page/home-page.module').then(m => m.HomePageModule),
  },/*
  {
    path: 'arbitration',
    data: {
      // requireAccess: 'RFieldMod'
    },
    children: ARBITRATION_PAGE_ROUTES,
  },*/
  {
    path: 'usuarios',
    data: {
      //requireAccess: 'RUser'
    },
    children: USER_PAGE_ROUTES,
  }
];
