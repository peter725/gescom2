import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SampleUploaderComponent } from "./sample-uploader.component";

describe("SampleUploaderComponent", () => {
  let component: SampleUploaderComponent;
  let fixture: ComponentFixture<SampleUploaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SampleUploaderComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SampleUploaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
