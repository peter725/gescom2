import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProtocolEditPageComponent } from './protocol-copy-page.component';

describe('ProtocolEditPageComponent', () => {
  let component: ProtocolEditPageComponent;
  let fixture: ComponentFixture<ProtocolEditPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolEditPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolEditPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
