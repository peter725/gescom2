import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SectionLabelComponent } from "./section-label.component";

describe("SectionItemComponent", () => {
  let component: SectionLabelComponent;
  let fixture: ComponentFixture<SectionLabelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SectionLabelComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SectionLabelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
