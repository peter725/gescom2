import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LazyModuleLoadingComponent } from './lazy-module-loading.component';

describe('LazyModuleLoadingComponent', () => {
  let component: LazyModuleLoadingComponent;
  let fixture: ComponentFixture<LazyModuleLoadingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LazyModuleLoadingComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(LazyModuleLoadingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
