
import { ComponentFixture, TestBed } from '@angular/core/testing';
import {
  InfringementListPageComponent
} from '@base/pages/infringement/infringement-list-page/infringement-list-page.component';

describe('InfringementListPageComponent', () => {
  let component: InfringementListPageComponent;
  let fixture: ComponentFixture<InfringementListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfringementListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InfringementListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});