import { Routes } from '@angular/router';
import { ProtocolAddPageModule } from '@base/pages/protocol/protocol-add-page/protocol-add-page.module';

const breadcrumb = 'pages.protocol.title';

export const PROTOCOL_PAGE_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'consulta',
    pathMatch: 'full',
  },
  {
    path: '0',
    data: {
      breadcrumb,
    },
    loadChildren: () => import('./').then(m => m.ProtocolAddPageModule),
  },

];
