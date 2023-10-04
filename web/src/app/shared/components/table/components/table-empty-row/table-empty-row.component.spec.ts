import { ComponentFixture, TestBed } from "@angular/core/testing";

import { TableEmptyRowComponent } from "./table-empty-row.component";

describe("TableEmptyRowComponent", () => {
  let component: TableEmptyRowComponent;
  let fixture: ComponentFixture<TableEmptyRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TableEmptyRowComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TableEmptyRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
