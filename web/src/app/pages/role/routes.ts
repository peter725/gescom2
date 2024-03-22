import { Routes } from '@angular/router';

const breadcrumb = 'pages.role.title';

export const ROLE_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full',
  },
  {
    path: 'consulta',
    data: { breadcrumb, requireAccess: 'role', requireScope: 'rr' },
    loadChildren: () => import('./').then(m => m.RoleListPageModule),
  },
  {
    path: 'alta',
    data: {
      breadcrumb,
      requireAccess: 'role',
      requireScope: 'ww'
    },
    loadChildren: () => import('./').then(m => m.RoleEditPageModule),
  },
  {
    path: ':id',
    data: {
      breadcrumb,
      requireAccess: 'role',
      requireScope: 'ww'
    },
    loadChildren: () => import('./').then(m => m.RoleEditPageModule),
  },
];
