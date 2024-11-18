import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleListPageFilterComponent } from './role-list-page-filter.component';

describe('UserListPageFilterComponent', () => {
  let component: RoleListPageFilterComponent;
  let fixture: ComponentFixture<RoleListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RoleListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(RoleListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
