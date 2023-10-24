import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleDatasetColTextComponent } from "./sample-dataset-col-text.component";

describe("SampleDatasetColTextComponent", () => {
  let component: SampleDatasetColTextComponent;
  let fixture: ComponentFixture<SampleDatasetColTextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleDatasetColTextComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleDatasetColTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
