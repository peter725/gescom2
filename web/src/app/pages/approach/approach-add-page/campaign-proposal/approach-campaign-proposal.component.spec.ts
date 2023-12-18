import{ ApproachCampaignProposalComponent } from './approach-campaign-proposal.component';
import {ComponentFixture, TestBed} from "@angular/core/testing";

describe("FieldEditPageComponent",() => {
    let component: ApproachCampaignProposalComponent;
    let fixture: ComponentFixture<ApproachCampaignProposalComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ApproachCampaignProposalComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(ApproachCampaignProposalComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it("should create", () => {
        expect(component).toBeTruthy();
    });

});