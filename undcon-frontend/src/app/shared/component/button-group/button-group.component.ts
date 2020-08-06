import { Customer } from './../../../core/model/customer';
import { Component, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-button-group',
  templateUrl: './button-group.component.html',
  styleUrls: ['./button-group.component.scss']
})
export class ButtonGroupComponent implements AfterViewInit {

  private buttonSelected;

  @Output() itemSelected: EventEmitter<string> = new EventEmitter();

  @Input() button1;
  @Input() button2;
  @Input() button3;
  @Input() button4;
  @Input() selectDefaultItem;

  heroes = HEROES;

  ngAfterViewInit() {
    if (this.selectDefaultItem) {
      this.select(this.selectDefaultItem);
    }
  }

  mouseover(id: string) {
    this.setIntermediateColor(id);
  }

  mousedown(id: string) {
    if (this.buttonSelected && this.buttonSelected === id) {
      this.setSelectColor(id);
    } else {
      this.setDisableColor(id);
    }
  }

  select(id: string) {
    if (this.buttonSelected && this.buttonSelected !== id) {
      this.setDisableColor(this.buttonSelected);
    }
    this.setSelectColor(id);
    this.buttonSelected = id;
    this.itemSelected.emit(this.buttonSelected);
  }

  private setDisableColor(id: string) {
    this.setColor(id, 'rgb(139, 180, 248)');
  }

  private setIntermediateColor(id: string) {
    this.setColor(id, 'rgb(66, 133, 244)');
  }

  private setSelectColor(id: string) {
    this.setColor(id, 'rgb(14, 97, 234)');
  }

  private setColor(id: string, color: string) {
    document.getElementById(id).style.backgroundColor = color;
  }
}

export const HEROES: Customer[] = [
  { id: 11, name: 'Dr Nice', phone: '' },
  { id: 12, name: 'Narco', phone: '' },
  { id: 13, name: 'Bombasto', phone: '' },
  { id: 14, name: 'Celeritas', phone: '' },
  { id: 15, name: 'Magneta', phone: '' },
  { id: 16, name: 'RubberMan', phone: '' },
  { id: 17, name: 'Dynama', phone: '' },
  { id: 18, name: 'Dr IQ', phone: '' },
  { id: 19, name: 'Magma', phone: '' },
  { id: 20, name: 'Tornado', phone: '' }
];
