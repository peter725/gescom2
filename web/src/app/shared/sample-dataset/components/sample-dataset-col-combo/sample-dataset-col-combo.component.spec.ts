import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetColComboComponent } from "./sample-dataset-col-combo.component";

describe("SampleDatasetColComboComponent", () => {
  let component: SampleDatasetColComboComponent;
  let fixture: ComponentFixture<SampleDatasetColComboComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetColComboComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetColComboComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
