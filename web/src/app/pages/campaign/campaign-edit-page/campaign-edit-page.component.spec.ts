import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CampaignEditPageComponent } from '@base/pages/campaign/campaign-edit-page/campaign-edit-page.component';

describe('CampaignEditPageComponent', () => {
    let component: CampaignEditPageComponent;
    let fixture: ComponentFixture<CampaignEditPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [CampaignEditPageComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(CampaignEditPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});