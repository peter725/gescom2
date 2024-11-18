import { ComponentFixture, TestBed } from "@angular/core/testing";

import { HiddenFormComponent } from "./hidden-form.component";

describe("HiddenFormComponent", () => {
  let component: HiddenFormComponent;
  let fixture: ComponentFixture<HiddenFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HiddenFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(HiddenFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
