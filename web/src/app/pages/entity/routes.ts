import { Routes } from '@angular/router';

const breadcrumb = 'pages.entity.title';
const breadcrumbType = 'pages.entityType.title';

export const ENTITY_PAGE_ROUTES: Routes = [
    {
        path: '',
        redirectTo: 'consulta',
        pathMatch: 'full',
    },
    {
        path: 'consulta',
        data: { breadcrumb, requireAccess: 'entity', requireScope: 'rr' },
        loadChildren: () => import('./').then(m => m.EntityListPageModule),
    },
    {
        path: '0',
        data: {
            breadcrumb,
            requireAccess: 'entity',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.EntityEditPageModule),
    },
    {
        path: ':id',
        data: {
            breadcrumb,
            requireAccess: 'entity',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.EntityEditPageModule),
    },
    {
        path: 'tipo/consulta',
        data: { breadcrumb: breadcrumbType, requireAccess: 'entity', requireScope: 'rr' },
        loadChildren: () => import('./').then(m => m.EntityTypeListPageModule),
    },
    {
        path: 'tipo/alta',
        data: {
            breadcrumb: breadcrumbType,
            requireAccess: 'entity',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.EntityTypeEditPageModule),
    },
    {
        path: 'tipo/:id',
        data: {
            breadcrumb: breadcrumbType,
            requireAccess: 'entity',
            requireScope: 'ww'
        },
        loadChildren: () => import('./').then(m => m.EntityTypeEditPageModule),
    },
];
