import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryConfigComponent } from "./data-query-config.component";

describe("DataQueryConfigComponent", () => {
  let component: DataQueryConfigComponent;
  let fixture: ComponentFixture<DataQueryConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryConfigComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
