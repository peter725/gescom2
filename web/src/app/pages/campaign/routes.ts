import {Routes} from "@angular/router";

const breadcrumb = 'pages.campaign.title';

export const CAMPAIGN_PAGE_ROUTES: Routes = [
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
        loadChildren: () => import('./').then(m => m.CampaignListPageModule),
    },
    {
        path: '',
        data: {breadcrumb},
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
    //
    {
        path: '0',
        data: {
            breadcrumb,
            // requireAccess: 'WField'
        },
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
    {
        path: ':id',
        data: {
        breadcrumb,
    },
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
  ]