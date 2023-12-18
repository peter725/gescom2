import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetColComboMultiComponent } from "./sample-dataset-col-combo-multi.component";

describe("SampleDatasetColComboMultiComponent", () => {
  let component: SampleDatasetColComboMultiComponent;
  let fixture: ComponentFixture<SampleDatasetColComboMultiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetColComboMultiComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetColComboMultiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
