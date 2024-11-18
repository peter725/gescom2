import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryViewFilterComponent } from "./data-query-view-filter.component";

describe("DataQueryViewFilterComponent", () => {
  let component: DataQueryViewFilterComponent;
  let fixture: ComponentFixture<DataQueryViewFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryViewFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryViewFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
