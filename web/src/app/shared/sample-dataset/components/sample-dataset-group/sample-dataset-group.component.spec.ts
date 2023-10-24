import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetGroupComponent } from "./sample-dataset-group.component";

describe("SampleDatasetGroupComponent", () => {
  let component: SampleDatasetGroupComponent;
  let fixture: ComponentFixture<SampleDatasetGroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetGroupComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
