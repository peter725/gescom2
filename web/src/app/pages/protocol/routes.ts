import { Routes } from '@angular/router';

const breadcrumb = 'pages.protocol.title';

export const PROTOCOL_PAGE_ROUTES: Routes = [
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
    loadChildren: () => import('./').then(m => m.ProtocolListPageModule),
  },
  {
    path: '0',
    data: {
      breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.ProtocolAddPageModule),
  },
  {
    path: ':id/ver',
    data: {
        breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.ProtocolSeePageModule),
  },
  {
    path: ':id',
    data: {
        breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.ProtocolEditPageModule),
  },

];
