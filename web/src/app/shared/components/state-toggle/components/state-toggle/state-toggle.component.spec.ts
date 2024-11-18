import { ComponentFixture, TestBed } from "@angular/core/testing";

import { StateToggleComponent } from "./state-toggle.component";

describe("StateToggleComponent", () => {
  let component: StateToggleComponent;
  let fixture: ComponentFixture<StateToggleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StateToggleComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(StateToggleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
