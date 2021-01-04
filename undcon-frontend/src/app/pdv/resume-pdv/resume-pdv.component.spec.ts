import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumePdvComponent } from './resume-pdv.component';

describe('ResumePdvComponent', () => {
  let component: ResumePdvComponent;
  let fixture: ComponentFixture<ResumePdvComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResumePdvComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResumePdvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
