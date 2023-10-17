import { ComponentFixture, TestBed } from "@angular/core/testing";

import { FilterHiddenComponent } from "./filter-hidden.component";

describe("FilterHiddenComponent", () => {
  let component: FilterHiddenComponent;
  let fixture: ComponentFixture<FilterHiddenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FilterHiddenComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FilterHiddenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
