import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LazyModuleLoadErrorComponent } from './lazy-module-load-error.component';

describe('LazyModuleLoadErrorComponent', () => {
  let component: LazyModuleLoadErrorComponent;
  let fixture: ComponentFixture<LazyModuleLoadErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LazyModuleLoadErrorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(LazyModuleLoadErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
