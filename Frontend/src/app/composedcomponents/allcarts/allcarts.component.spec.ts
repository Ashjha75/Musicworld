import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllcartsComponent } from './allcarts.component';

describe('AllcartsComponent', () => {
  let component: AllcartsComponent;
  let fixture: ComponentFixture<AllcartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllcartsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllcartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
