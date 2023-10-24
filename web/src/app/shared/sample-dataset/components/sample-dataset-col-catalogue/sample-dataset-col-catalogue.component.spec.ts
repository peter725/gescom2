import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetColCatalogueComponent } from "./sample-dataset-col-catalogue.component";

describe("SampleDatasetColCatalogueComponent", () => {
  let component: SampleDatasetColCatalogueComponent;
  let fixture: ComponentFixture<SampleDatasetColCatalogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetColCatalogueComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetColCatalogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
