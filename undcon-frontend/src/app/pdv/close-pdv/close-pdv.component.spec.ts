import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClosePdvComponent } from './close-pdv.component';

describe('ClosePdvComponent', () => {
  let component: ClosePdvComponent;
  let fixture: ComponentFixture<ClosePdvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClosePdvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClosePdvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
