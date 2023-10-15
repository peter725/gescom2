import { ComponentFixture, TestBed } from "@angular/core/testing";

import { UserEditScopeFormComponent } from "./user-edit-scope-form.component";

describe("UserEditScopeFormComponent", () => {
  let component: UserEditScopeFormComponent;
  let fixture: ComponentFixture<UserEditScopeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserEditScopeFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(UserEditScopeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
