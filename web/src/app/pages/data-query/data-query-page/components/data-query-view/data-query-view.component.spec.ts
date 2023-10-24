import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryViewComponent } from "./data-query-view.component";

describe("DataQueryViewComponent", () => {
  let component: DataQueryViewComponent;
  let fixture: ComponentFixture<DataQueryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryViewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
