import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { TableValues } from '@shared/model/table';
import { Page } from '@model/page';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  pageActual: number = 0;
  tableItems = [];
  totalItems = 0;

  @Output() reloadItems: EventEmitter<number> = new EventEmitter();
  @Output() onClickItem: EventEmitter<any> = new EventEmitter();
  @Input() tableValues: TableValues[] = [];
  @Input() set pageValues(value: Page<any>) {
    this.tableItems = value.content;
    this.totalItems = value.totalElements;
  }

  constructor() { }

  ngOnInit(): void {
  }

  getHeaders(tableValue: TableValues) {
    return tableValue.columnTitle;
  }

  getFieldsOfTable(item, tableValue: TableValues) {
    const split = tableValue.field.split('.');
    if (split.length > 1) {
      const value = item[split[0]];
      if (value) {
        return value[split[1]]
      }
      return '';
    }
    return item[tableValue.field]
  }

  updateItems(page) {
    this.pageActual = page;
    this.reloadItems.emit(page-1);
  }

  clickItem(item) {
    this.onClickItem.emit(item);
  }
}