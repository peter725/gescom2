import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardWrapperPageComponent } from './dashboard-wrapper-page.component';

describe('DashboardWrapperPageComponent', () => {
  let component: DashboardWrapperPageComponent;
  let fixture: ComponentFixture<DashboardWrapperPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardWrapperPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DashboardWrapperPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
