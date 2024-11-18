import {
  InfringementListPageFilterComponent
} from '@base/pages/campaign/campaign-see-page/components/infringement/infringement-list-page/components/infringement-list-page-filter/infringement-list-page-filter.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';

describe('InfringementListPageFilterComponent', () => {
  let component: InfringementListPageFilterComponent;
  let fixture: ComponentFixture<InfringementListPageFilterComponent > ;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfringementListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InfringementListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});