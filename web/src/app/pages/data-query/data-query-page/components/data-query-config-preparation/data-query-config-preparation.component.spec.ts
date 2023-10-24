import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryConfigPreparationComponent } from "./data-query-config-preparation.component";

describe("DataQueryConfigPreparationComponent", () => {
  let component: DataQueryConfigPreparationComponent;
  let fixture: ComponentFixture<DataQueryConfigPreparationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryConfigPreparationComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryConfigPreparationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
