import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProtocolListPageComponent } from './protocol-list-page.component';

describe('FieldListPageComponent', () => {
  let component: ProtocolListPageComponent;
  let fixture: ComponentFixture<ProtocolListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolListPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
