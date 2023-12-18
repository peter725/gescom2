import {Routes} from "@angular/router";

const breadcrumb = 'pages.approach.title';

export const APPROACH_PAGE_ROUTES: Routes = [
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
        loadChildren: () => import('./').then(m => m.ApproachListPageModule),
    },
    {
        path: '0',
        data: {
            breadcrumb,
            // requireAccess: 'WField'
        },
        loadChildren: () => import('./').then(m => m.ApproachCampaignProposalModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
        },
        loadChildren: () => import('./').then(m => m.ApproachSeePageModule),
    },
  ]