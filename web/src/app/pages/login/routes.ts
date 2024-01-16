import { Routes } from '@angular/router';


export const AUTHENTICATION_PAGE_ROUTES: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login',
  },
  {
    path: 'login',
    data: { breadcrumb: 'pages.login.title' },
    loadChildren: () => import('./').then(m => m.LoginPageModule),
  },
  {
    path: 'logout',
    data: { breadcrumb: 'pages.logout.title' },
    loadChildren: () => import('./').then(m => m.LogoutPageModule),
    //canActivate: [IsAuthenticatedGuard],
    //canActivateChild: [IsAuthenticatedGuard],
    //canLoad: [IsAuthenticatedGuard],
  },
];
