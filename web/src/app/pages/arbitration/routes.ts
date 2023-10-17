import { Routes } from '@angular/router';

const breadcrumb = 'pages.arbitration.title';

export const ARBITRATION_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: '0',
    pathMatch: 'full',
  },
  // {
  //   path: 'consulta',
  //   data: { breadcrumb },
  //   loadChildren: () => import('./').then(m => m.FieldListPageModule),
  // },
  {
    path: '0',
    data: {
      breadcrumb,
      // requireAccess: 'WField'
    },
    loadChildren: () => import('./').then(m => m.ArbitrationStep1Module),
  },
  {
    path: 'documents',
    data: {
      breadcrumb,
      // requireAccess: 'WField'
    },
    loadChildren: () => import('./').then(m => m.ArbitrationStep2Module),
  },
  {
    path: 'sign',
    data: {
      breadcrumb,
      // requireAccess: 'WField'
    },
    loadChildren: () => import('./').then(m => m.ArbitrationStep3Module),
  }
  // {
  //   path: ':id',
  //   data: {
  //     breadcrumb,
  //     requireAccess: 'WField'
  //   },
  //   loadChildren: () => import('./').then(m => m.FieldEditPageModule),
  // }
];
