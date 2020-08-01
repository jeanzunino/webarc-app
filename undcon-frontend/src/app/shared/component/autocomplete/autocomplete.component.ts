import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Entity } from '@app/core/model/entity';
import { Page } from '@app/core/model/page';
import { EntityService } from '@app/core/service/entity/entity.service';
import { QueryFilterEnum } from '@app/core/enum/query-filter';

@Component({
  selector: 'app-autocomplete',
  templateUrl: './autocomplete.component.html',
  styleUrls: ['./autocomplete.component.scss']
})
export class AutocompleteComponent {

  @Output() selectEvent: EventEmitter<any> = new EventEmitter();
  @Output() changedEvent: EventEmitter<any> = new EventEmitter();
  @Input() keyword = '';
  @Input() set initialValue(value) {
    this.batata = value;
  }
  @Input() notFoundText = '';
  @Input() autocompleteValueSearch = 'name';
  @Input() minQueryLength = 3;
  @Input() set setValues(values: any[]) {
    this.data = values;
  }

  data: any[] = [];
  batata:any;

  constructor() {
    debugger
    console.log(this.keyword)
    console.log(this.initialValue)
    console.log(this.notFoundText)
    console.log(this.notFoundText)
    console.log(this.minQueryLength)
    console.log(this.data)
  }

  select(item) {
    this.selectEvent.emit(item);
  }

  async changed(value) {
    this.changedEvent.emit(value);
  }
}
