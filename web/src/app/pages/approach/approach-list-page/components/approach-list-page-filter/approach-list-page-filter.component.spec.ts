import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproachListPageFilterComponent } from './approach-list-page-filter.component';

describe('UserListPageFilterComponent', () => {
  let component: ApproachListPageFilterComponent;
  let fixture: ComponentFixture<ApproachListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApproachListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ApproachListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
