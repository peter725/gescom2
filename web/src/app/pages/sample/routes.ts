import { Routes } from '@angular/router';

const breadcrumb = 'pages.sample.title';

export const SAMPLE_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full',
  },
  {
    path: 'consulta',
    data: { breadcrumb },
    loadChildren: () => import('./').then(m => m.SampleListPageModule),
  },
  {
    path: 'alta',
    data: {
      breadcrumb,
      requireAccess: 'WSample'
    },
    loadChildren: () => import('./').then(m => m.SampleEditPageModule),
  },
  {
    path: ':id',
    data: {
      breadcrumb,
      requireAccess: 'WSample'
    },
    loadChildren: () => import('./').then(m => m.SampleEditPageModule),
  }
];
