import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleEditPageComponent } from './sample-edit-page.component';

describe('SampleEditPageComponent', () => {
  let component: SampleEditPageComponent;
  let fixture: ComponentFixture<SampleEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleEditPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
