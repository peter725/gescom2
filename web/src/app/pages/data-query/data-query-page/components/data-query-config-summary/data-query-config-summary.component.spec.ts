import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryConfigSummaryComponent } from "./data-query-config-summary.component";

describe("DataQueryConfigSummaryComponent", () => {
  let component: DataQueryConfigSummaryComponent;
  let fixture: ComponentFixture<DataQueryConfigSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryConfigSummaryComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryConfigSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
