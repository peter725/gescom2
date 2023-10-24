import { ComponentFixture, TestBed } from "@angular/core/testing";

import { ContextChangeLoaderComponent } from "./context-change-loader.component";

describe("ContextChangeLoaderComponent", () => {
    let component: ContextChangeLoaderComponent;
    let fixture: ComponentFixture<ContextChangeLoaderComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ContextChangeLoaderComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(ContextChangeLoaderComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
