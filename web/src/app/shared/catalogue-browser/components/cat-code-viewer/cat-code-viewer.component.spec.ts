import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatCodeViewerComponent } from './cat-code-viewer.component';

describe('CatCodeViewerComponent', () => {
  let component: CatCodeViewerComponent;
  let fixture: ComponentFixture<CatCodeViewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CatCodeViewerComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CatCodeViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
