import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutonomousCommunityListPageComponent } from './autonomous-community-list-page.component';

describe('CcaaListPageComponent', () => {
    let component: AutonomousCommunityListPageComponent;
    let fixture: ComponentFixture<AutonomousCommunityListPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [AutonomousCommunityListPageComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(AutonomousCommunityListPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
