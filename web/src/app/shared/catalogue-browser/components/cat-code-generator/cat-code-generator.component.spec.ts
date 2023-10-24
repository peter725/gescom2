import { ComponentFixture, TestBed } from "@angular/core/testing";

import { CatCodeGeneratorComponent } from "./cat-code-generator.component";

describe("CatCodeGeneratorComponent", () => {
  let component: CatCodeGeneratorComponent;
  let fixture: ComponentFixture<CatCodeGeneratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CatCodeGeneratorComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CatCodeGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
