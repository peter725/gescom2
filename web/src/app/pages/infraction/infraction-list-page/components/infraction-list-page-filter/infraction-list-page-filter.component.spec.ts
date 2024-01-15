import {
  InfractionListPageFilterComponent
} from '@base/pages/infraction/infraction-list-page/components/infraction-list-page-filter/infraction-list-page-filter.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';

describe('InfractionListPageFilterComponent', () => {
  let component: InfractionListPageFilterComponent;
  let fixture: ComponentFixture<InfractionListPageFilterComponent > ;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfractionListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InfractionListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});