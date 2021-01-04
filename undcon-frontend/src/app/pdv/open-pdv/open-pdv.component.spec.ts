import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenPdvComponent } from './open-pdv.component';

describe('OpenPdvComponent', () => {
  let component: OpenPdvComponent;
  let fixture: ComponentFixture<OpenPdvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenPdvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenPdvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
