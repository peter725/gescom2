import { ComponentFixture, TestBed } from "@angular/core/testing";

import { ArbitrationStep2Component } from "./arbitration-step2.component";

describe("FieldEditPageComponent", () => {
  let component: ArbitrationStep2Component;
  let fixture: ComponentFixture<ArbitrationStep2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbitrationStep2Component],
    }).compileComponents();

    fixture = TestBed.createComponent(ArbitrationStep2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
