import { Routes } from '@angular/router';

const breadcrumb = 'pages.user.title';

export const USER_PAGE_ROUTES: Routes = [
  {
    path: '0',
    data: {
      breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.UserAddPageModule),
  },
  {
    path: 'consulta',
    data: {
      breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.UserListPageModule),
  }
];
