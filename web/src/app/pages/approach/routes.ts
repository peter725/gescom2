import {Routes} from "@angular/router";
import {
    ApproachCampaignProposalModule
} from "@base/pages/approach/approach-add-page/campaign-proposal/approach-campaign-proposal.module";

const breadcrumb = 'pages.approach.title';

export const APPROACH_PAGE_ROUTES: Routes = [
    {
        path: '',
        data: {breadcrumb},
        loadChildren: () => import('./').then(m => m.ApproachCampaignProposalModule),
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
    }
  ]