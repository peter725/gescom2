import { CampaignSeePageComponent } from './campaign-see-page.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';


describe('CampaignAddPageComponent', () => {
  let component: CampaignSeePageComponent;
  let fixture: ComponentFixture<CampaignSeePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CampaignSeePageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CampaignSeePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
