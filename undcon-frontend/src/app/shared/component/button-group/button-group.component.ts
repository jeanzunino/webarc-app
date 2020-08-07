import { ButtonGroupValues } from '@shared/model/button-group';
import { Component, Input, Output, EventEmitter, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-button-group',
  templateUrl: './button-group.component.html',
  styleUrls: ['./button-group.component.scss']
})
export class ButtonGroupComponent implements AfterViewInit {

  private buttonSelected;

  @Output() selected: EventEmitter<string> = new EventEmitter();
  @Input() buttonGroupValues: ButtonGroupValues[] = [];
  @Input() selectDefaultItem;
  @Input() textColor = 'color-black';

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
    this.selected.emit(this.buttonSelected);
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
