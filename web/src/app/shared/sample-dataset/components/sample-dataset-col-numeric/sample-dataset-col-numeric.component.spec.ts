import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetColNumericComponent } from "./sample-dataset-col-numeric.component";

describe("SampleDatasetColNumericComponent", () => {
  let component: SampleDatasetColNumericComponent;
  let fixture: ComponentFixture<SampleDatasetColNumericComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetColNumericComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetColNumericComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
