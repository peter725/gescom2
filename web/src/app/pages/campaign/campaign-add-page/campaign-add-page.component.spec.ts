import { CampaignAddPageComponent } from './campaign-add-page.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';


describe('CampaignAddPageComponent', () => {
  let component: CampaignAddPageComponent;
  let fixture: ComponentFixture<CampaignAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CampaignAddPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CampaignAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
