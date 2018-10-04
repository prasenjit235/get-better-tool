import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApprisalComponent } from './apprisal.component';

describe('ApprisalComponent', () => {
  let component: ApprisalComponent;
  let fixture: ComponentFixture<ApprisalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApprisalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApprisalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
