import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArbitrationListPageComponent } from './field-list-page.component';

describe('FieldListPageComponent', () => {
  let component: ArbitrationListPageComponent;
  let fixture: ComponentFixture<ArbitrationListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbitrationListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ArbitrationListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
