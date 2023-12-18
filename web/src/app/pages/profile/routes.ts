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
    data: { breadcrumb },
    loadChildren: () => import('./').then(m => m.ProfileListPageModule),
  },
  {
    path: 'alta',
    data: {
      breadcrumb,
      //requireAccess: 'WProfile'
    },
    loadChildren: () => import('./').then(m => m.ProfileEditPageModule),
  },
  {
    path: ':id',
    data: {
      breadcrumb,
      //requireAccess: 'WProfile'
    },
    loadChildren: () => import('./').then(m => m.ProfileEditPageModule),
  },
];
