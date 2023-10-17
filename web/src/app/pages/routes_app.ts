import {Routes} from '@angular/router';
import { USER_PAGE_ROUTES } from './user/routes';


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
  }
];
