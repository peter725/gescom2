import { ComponentFixture, TestBed } from '@angular/core/testing';
import {
  ProtocolListPageFilterComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-list-page/components/protocol-list-page-filter/protocol-list-page-filter.component';
import {
  ProtocolListPageComponent
} from '@base/pages/campaign/campaign-see-page/components/protocol/protocol-list-page/protocol-list-page.component';

describe('ProtocolListPageFilterComponent', () => {
  let component: ProtocolListPageFilterComponent;
  let fixture: ComponentFixture<ProtocolListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProtocolListPageFilterComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ProtocolListPageFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});