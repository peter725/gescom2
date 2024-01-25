
import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {
    ProtocolAddPageComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-add-page/protocol-add-page.component';

const routes: Routes = [
    {
        path: '',
        component: ProtocolAddPageComponent,
        title: 'pages.protocol.add',
        data: {breadcrumb: 'generic.actions.add'}
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProtocolAddPageRountingModule {
}