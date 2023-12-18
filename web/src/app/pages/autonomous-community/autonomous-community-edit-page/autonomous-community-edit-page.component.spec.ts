import { ComponentFixture, TestBed } from "@angular/core/testing";

import { AutonomousCommunityEditPageComponent } from "./autonomous-community-edit-page.component";

describe("AutonomousCommunityEditPageComponent", () => {
    let component: AutonomousCommunityEditPageComponent;
    let fixture: ComponentFixture<AutonomousCommunityEditPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [AutonomousCommunityEditPageComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(AutonomousCommunityEditPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it("should create", () => {
        expect(component).toBeTruthy();
    });
});
