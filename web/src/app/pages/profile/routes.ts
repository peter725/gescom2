import { Routes } from '@angular/router';

const breadcrumb = 'pages.profile.title';

export const PROFILE_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full',
  },
  {
    path: 'consulta',
    data: { breadcrumb, requireAccess: 'profile', requireScope: 'rr' },
    loadChildren: () => import('./').then(m => m.ProfileListPageModule),
  },
  {
    path: 'alta',
    data: {
      breadcrumb,
      requireAccess: 'profile',
      requireScope: 'ww'
    },
    loadChildren: () => import('./').then(m => m.ProfileEditPageModule),
  },
  {
    path: ':id',
    data: {
      breadcrumb,
      requireAccess: 'profile',
      requireScope: 'ww'
    },
    loadChildren: () => import('./').then(m => m.ProfileEditPageModule),
  },
];
