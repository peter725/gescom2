import { Routes } from '@angular/router';

const breadcrumb = 'pages.dataQuery.title';

export const DATA_QUERY_PAGE_ROUTES: Routes = [
    {
        path: '',
        redirectTo: 'consulta',
        pathMatch: 'full',
    },
    {
        path: 'consulta',
        data: { breadcrumb },
        loadChildren: () => import('./').then(m => m.DataQueryPageModule),
    },
    /*   {
        path: '0',
        title: 'Create',
        data: {
          breadcrumb,
          requireAccess: 'WPlan'
        },
        loadChildren: () => import('./').then(m => m.PlanEditPageModule),
      },
      {
        path: ':planCode/:id',
        title: 'Edit',
        data: {
          breadcrumb,
          requireAccess: 'WPlan',
        },
        loadChildren: () => import('./').then(m => m.PlanEditPageModule)
      }, */
];
