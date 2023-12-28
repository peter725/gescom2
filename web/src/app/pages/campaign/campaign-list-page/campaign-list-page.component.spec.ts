import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CampaignListPageComponent } from '@base/pages/campaign/campaign-list-page/campaign-list-page.component';

describe('CampaignListPageComponent', () => {
  let component: CampaignListPageComponent;
  let fixture: ComponentFixture<CampaignListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CampaignListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CampaignListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});