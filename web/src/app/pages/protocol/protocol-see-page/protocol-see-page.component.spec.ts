import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProtocolSeePageComponent } from './protocol-see-page.component';

describe('ProtocolSeePageComponent', () => {
  let component: ProtocolSeePageComponent;
  let fixture: ComponentFixture<ProtocolSeePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolSeePageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolSeePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
