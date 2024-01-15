import {
  InfractionListPageComponent
} from '@base/pages/infraction/infraction-list-page/infraction-list-page.component';
import { ComponentFixture, TestBed } from '@angular/core/testing';

describe('InfractionListPageComponent', () => {
  let component: InfractionListPageComponent;
  let fixture: ComponentFixture<InfractionListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfractionListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InfractionListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});