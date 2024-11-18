import { ComponentFixture, TestBed } from "@angular/core/testing";

import { DataQueryPageComponent } from "./data-query-page.component";

describe("DataQueryPageComponent", () => {
    let component: DataQueryPageComponent;
    let fixture: ComponentFixture<DataQueryPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [DataQueryPageComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(DataQueryPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
