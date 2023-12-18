import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleListPageFilterComponent } from "./sample-list-page-filter.component";

describe("SampleSeasonListPageFilterComponent", () => {
  let component: SampleListPageFilterComponent;
  let fixture: ComponentFixture<SampleListPageFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
