import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableValues, TableFieldCustomizationEmitter } from '@shared/model/table';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  pageActual: number = 0
  tableItems = []

  @Output() reloadItems: EventEmitter<number> = new EventEmitter();
  @Output() onClickItem: EventEmitter<any> = new EventEmitter();
  @Input() totalItems = 0;
  @Input() tableValues: TableValues[] = [];
  @Input() set items(value: []) {
    this.tableItems = value;
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
      return item[split[0]][split[1]]
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