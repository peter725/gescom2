import {Routes} from '@angular/router';
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

  },

];
