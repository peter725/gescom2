import {Routes} from '@angular/router';
import { AUTHENTICATION_PAGE_ROUTES } from '@base/pages/login';
import { IsAuthenticatedGuard } from '@libs/security';
// import { CanAccessGuard, IsAuthenticatedGuard } from '@libs/security';

export const routes: Routes = [
  {
    path: '',
    title: 'Gesco',
    pathMatch: 'full',
    redirectTo: '/app/inicio'
  },
  {
    path: 'app',
    loadChildren: () => import('./dashboard-wrapper-page').then(m => m.DashboardWrapperPageModule),
    //canActivate: [IsAuthenticatedGuard],
    //canActivateChild: [IsAuthenticatedGuard,],
   //canLoad: [IsAuthenticatedGuard,],
  },
  {
    path: 'auth',
    children: AUTHENTICATION_PAGE_ROUTES,
  },
  {
    path: '**',
    loadChildren: () => import('./error/not-found-page/not-found-page.module').then(m => m.NotFoundPageModule)
  },

];
