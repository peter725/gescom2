import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProtocolListPageFilterComponent } from './protocol-list-page-filter.component';

describe('UserListPageFilterComponent', () => {
  let component: ProtocolListPageFilterComponent;
  let fixture: ComponentFixture<ProtocolListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
