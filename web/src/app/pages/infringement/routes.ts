import { Routes } from '@angular/router';

const   breadcrumb = 'pages.infringement.title';

export const INFRINGEMENT_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full',
  },
  {
    path: 'consulta',
    data: {
      breadcrumb,
    },
    loadChildren: () => import('./index').then(m => m.InfringementListPageModule),
  },
];