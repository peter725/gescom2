import {Routes} from "@angular/router";

const breadcrumb = 'pages.sampleSeason.title';

export const CAMPAIGN_PAGE_ROUTES: Routes = [
    {
        path: '',
        data: {breadcrumb},
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
    // {
    //     path: 'consulta',
    //     data: {
    //         breadcrumb,
    //     },
    //     loadChildren: () => import('./').then(m => m.ApproachListPageModule),
    // },
    {
        path: '0',
        data: {
            breadcrumb,
            // requireAccess: 'WField'
        },
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
    // {
    //     path: ':id',
    //     data: {
    //         breadcrumb,
    //     },
    //     loadChildren: () => import('./').then(m => m.ApproachSeePageModule),
    // },
  ]