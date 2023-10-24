import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListPageFilterComponent } from './user-list-page-filter.component';

describe('UserListPageFilterComponent', () => {
  let component: UserListPageFilterComponent;
  let fixture: ComponentFixture<UserListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(UserListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
