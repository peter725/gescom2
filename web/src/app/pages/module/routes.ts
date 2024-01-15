import { Routes } from '@angular/router';

const breadcrumb = 'pages.module.title';
const breadcrumbType = 'pages.moduleType.title';

export const MODULE_PAGE_ROUTES: Routes = [
    {
        path: '',
        redirectTo: 'consulta',
        pathMatch: 'full',
    },
    {
        path: 'consulta',
        data: { breadcrumb },
        loadChildren: () => import('./').then(m => m.ModuleListPageModule),
    },
    {
        path: '0',
        data: {
            breadcrumb,
            //requireAccess: 'WModule'
        },
        loadChildren: () => import('./').then(m => m.ModuleEditPageModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            //requireAccess: 'WModule'
        },
        loadChildren: () => import('./').then(m => m.ModuleEditPageModule),
    },
    {
        path: 'tipo/consulta',
        data: { breadcrumb: breadcrumbType },
        loadChildren: () => import('./').then(m => m.ModuleTypeListPageModule),
    },
    {
        path: 'tipo/alta',
        data: {
            breadcrumb: breadcrumbType,
            //requireAccess: 'WModule'
        },
        loadChildren: () => import('./').then(m => m.ModuleTypeEditPageModule),
    },
    {
        path: 'tipo/:id',
        data: {
            breadcrumb: breadcrumbType,
            //requireAccess: 'WModule'
        },
        loadChildren: () => import('./').then(m => m.ModuleTypeEditPageModule),
    },
];
