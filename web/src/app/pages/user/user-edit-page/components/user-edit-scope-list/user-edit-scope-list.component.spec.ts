import { ComponentFixture, TestBed } from "@angular/core/testing";

import { UserEditScopeListComponent } from "./user-edit-scope-list.component";

describe("UserEditScopeListComponent", () => {
  let component: UserEditScopeListComponent;
  let fixture: ComponentFixture<UserEditScopeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserEditScopeListComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(UserEditScopeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
