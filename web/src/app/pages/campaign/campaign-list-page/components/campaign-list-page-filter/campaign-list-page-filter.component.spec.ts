import { CampaignListPageFilterComponent } from '@base/pages/campaign/campaign-list-page/components';
import { ComponentFixture, TestBed } from '@angular/core/testing';

describe('CampaignListPageFilterComponent', () => {
  let component: CampaignListPageFilterComponent;
  let fixture: ComponentFixture<CampaignListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CampaignListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CampaignListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});