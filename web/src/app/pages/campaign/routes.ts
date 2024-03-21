import {Routes} from "@angular/router";
import { ProtocolEditPageComponent } from "../protocol/protocol-edit-page/protocol-edit-page.component";

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
            requireAccess: 'campaign',
            requireScope: 'rr'
        },
        loadChildren: () => import('./').then(m => m.CampaignListPageModule),
    },
    {
        path: '0',
        data: {
            breadcrumb,
            requireAccess: 'campaign',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.CampaignAddPageModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            requireAccess: 'campaign',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.CampaignEditPageModule),
    },
    {
        path: ':id/ver',
        data: {
            breadcrumb,
            requireAccess: 'campaign',
            requireScope: 'rr'
        },
        loadChildren: () => import('./').then(m => m.CampaignSeePageModule),
    },
    {
        path: ':id/resultados',
        data: {
            breadcrumb,
            requireAccess: 'campaign'
        },
        loadChildren: () => import('./').then(m => m.ResultadosModule),
    },
    {
        path: ':id/resultadosFinales',
        data: {
            breadcrumb,
            requireAccess: 'campaign'
        },
        loadChildren: () => import('./').then(m => m.ResultadosFinalesModule),
    },
    {
        path: ':id/ipr',
        data: {
            breadcrumb,
            requireAccess: 'campaign'
        },
        loadChildren: () => import('./').then(m => m.IprModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            requireAccess: 'campaign',
            requireScope: 'ww'
        },
        loadChildren: () => import('../protocol/').then(m => m.ProtocolEditPageModule),
    },

]