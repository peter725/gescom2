import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproachEditPageComponent } from './approach-edit-page.component';

describe('UserEditPageComponent', () => {
    let component: ApproachEditPageComponent;
    let fixture: ComponentFixture<ApproachEditPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [ApproachEditPageComponent],
        }).compileComponents();

        fixture = TestBed.createComponent(ApproachEditPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
