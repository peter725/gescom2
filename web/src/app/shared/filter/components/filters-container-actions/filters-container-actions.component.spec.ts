import { ComponentFixture, TestBed } from "@angular/core/testing";

import { FiltersContainerActionsComponent } from "./filters-container-actions.component";

describe("FiltersContainerActionsComponent", () => {
  let component: FiltersContainerActionsComponent;
  let fixture: ComponentFixture<FiltersContainerActionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FiltersContainerActionsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FiltersContainerActionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
