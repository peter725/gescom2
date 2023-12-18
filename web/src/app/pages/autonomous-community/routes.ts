import { Routes } from '@angular/router';

const breadcrumb = 'pages.ccaa.title';

export const PROFILE_PAGE_ROUTES: Routes = [
    {
        path: '',
        redirectTo: 'consulta',
        pathMatch: 'full',
    },
    {
        path: 'consulta',
        data: { breadcrumb },
        loadChildren: () => import('./').then(m => m.AutonomousCommunityListPageModule),
    },
    {
        path: 'alta',
        data: {
            breadcrumb,
            //requireAccess: 'WProfile'
        },
        loadChildren: () => import('./').then(m => m.AutonomousCommunityEditPageModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            //requireAccess: 'WProfile'
        },
        loadChildren: () => import('./').then(m => m.AutonomousCommunityEditPageModule),
    },
];
