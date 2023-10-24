import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SampleEditPageConfigFormComponent } from './sample-edit-page-config-form.component';

describe('SampleEditPageConfigFormComponent', () => {
  let component: SampleEditPageConfigFormComponent;
  let fixture: ComponentFixture<SampleEditPageConfigFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleEditPageConfigFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleEditPageConfigFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
