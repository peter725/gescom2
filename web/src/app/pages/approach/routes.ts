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
            requireAccess: 'approach',
            requireScope: 'rr'
        },
        loadChildren: () => import('./').then(m => m.ApproachListPageModule),
    },
    {
        path: '0',
        data: {
            breadcrumb,
            requireAccess: 'approach',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.ApproachCampaignProposalModule),
    },
    {
        path: ':id/ver',
        data: {
            breadcrumb,
            requireAccess: 'approach',
            requireScope: 'rr'
        },
        loadChildren: () => import('./').then(m => m.ApproachSeePageModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            requireAccess: 'approach',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.ApproachEditPageModule),
    },
  ]