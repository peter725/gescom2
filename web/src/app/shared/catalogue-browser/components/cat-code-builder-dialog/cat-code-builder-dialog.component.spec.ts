import { ComponentFixture, TestBed } from "@angular/core/testing";

import { CatCodeBuilderDialogComponent } from "./cat-code-builder-dialog.component";

describe("CatCodeBuilderDialogComponent", () => {
  let component: CatCodeBuilderDialogComponent;
  let fixture: ComponentFixture<CatCodeBuilderDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CatCodeBuilderDialogComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CatCodeBuilderDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
