import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetCollectionHeaderComponent } from "./sample-dataset-collection-header.component";

describe("SampleDatasetCollectionHeaderComponent", () => {
  let component: SampleDatasetCollectionHeaderComponent;
  let fixture: ComponentFixture<SampleDatasetCollectionHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetCollectionHeaderComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetCollectionHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
