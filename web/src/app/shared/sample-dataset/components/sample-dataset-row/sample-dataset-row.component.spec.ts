import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetRowComponent } from "./sample-dataset-row.component";

describe("SampleDatasetRowComponent", () => {
  let component: SampleDatasetRowComponent;
  let fixture: ComponentFixture<SampleDatasetRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetRowComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
