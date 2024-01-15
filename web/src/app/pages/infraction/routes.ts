import { Routes } from '@angular/router';

const breadcrumb = 'pages.campaign.title';

export const INFRACTION_PAGE_ROUTES: Routes = [
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
    loadChildren: () => import('./').then(m => m.InfractionListPageModule),
  },
];