import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleListPageComponent } from "./sample-list-page.component";

describe("SampleListPageComponent", () => {
  let component: SampleListPageComponent;
  let fixture: ComponentFixture<SampleListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
