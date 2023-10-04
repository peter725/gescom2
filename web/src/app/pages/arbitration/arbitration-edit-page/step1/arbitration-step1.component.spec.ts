import {ComponentFixture, TestBed} from "@angular/core/testing";

import {ArbitrationStep1Component} from "./arbitration-step1.component";

describe("FieldEditPageComponent", () => {
    let component: ArbitrationStep1Component;
    let fixture: ComponentFixture<ArbitrationStep1Component>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ArbitrationStep1Component],
        }).compileComponents();

        fixture = TestBed.createComponent(ArbitrationStep1Component);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
