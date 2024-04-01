import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProtocolAddPageComponent } from './protocol-add-page.component';

describe('ProtocolAddPageComponent', () => {
  let component: ProtocolAddPageComponent;
  let fixture: ComponentFixture<ProtocolAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolAddPageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
