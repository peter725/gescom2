import { ComponentFixture, TestBed } from "@angular/core/testing";

import { ArbitrationStep3Component } from "./arbitration-step3.component";

describe("FieldEditPageComponent", () => {
  let component: ArbitrationStep3Component;
  let fixture: ComponentFixture<ArbitrationStep3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbitrationStep3Component],
    }).compileComponents();

    fixture = TestBed.createComponent(ArbitrationStep3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
