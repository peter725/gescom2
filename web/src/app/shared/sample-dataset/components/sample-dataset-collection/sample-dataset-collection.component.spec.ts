import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetCollectionComponent } from "./sample-dataset-collection.component";

describe("SampleDatasetCollectionComponent", () => {
  let component: SampleDatasetCollectionComponent;
  let fixture: ComponentFixture<SampleDatasetCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetCollectionComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
