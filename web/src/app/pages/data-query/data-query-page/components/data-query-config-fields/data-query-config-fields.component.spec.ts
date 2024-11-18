import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryConfigFieldsComponent } from "./data-query-config-fields.component";

describe("DataQueryConfigFieldsComponent", () => {
  let component: DataQueryConfigFieldsComponent;
  let fixture: ComponentFixture<DataQueryConfigFieldsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DataQueryConfigFieldsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(DataQueryConfigFieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
