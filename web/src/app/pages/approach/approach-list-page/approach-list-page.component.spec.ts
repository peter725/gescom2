import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproachListPageComponent } from './approach-list-page.component';

describe('FieldListPageComponent', () => {
  let component: ApproachListPageComponent;
  let fixture: ComponentFixture<ApproachListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApproachListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ApproachListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
