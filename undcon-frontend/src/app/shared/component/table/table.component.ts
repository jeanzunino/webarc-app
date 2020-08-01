import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MaskPipe } from 'ngx-mask';
import { TranslateService } from '@ngx-translate/core';

import { TableValues } from '@shared/model/table';
import { Page } from '@model/page';
import { FormatEnum } from '@app/core/enum/format-enum';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss'],
})
export class TableComponent implements OnInit {
  currentPage = 0;
  tableItems = [];
  totalItems = 0;
  controlCurrentPage = false;

  @Output() reloadItems: EventEmitter<number> = new EventEmitter();
  @Output() clickItem: EventEmitter<any> = new EventEmitter();
  @Input() tableValues: TableValues[] = [];
  @Input() set pageValues(value: Page<any>) {
    this.tableItems = value.content;
    this.totalItems = value.totalElements;
  }
  @Input() set updateCurrentPage(page: number) {
    this.controlCurrentPage = true;
    this.currentPage = page;
  }

  constructor(private maskPipe: MaskPipe,
              private translate: TranslateService) { }

  ngOnInit(): void {}

  getHeaders(tableValue: TableValues) {
    return tableValue.columnTitle;
  }

  getFieldsOfTable(item, tableValue: TableValues) {
    let finalValue = '';
    const split = tableValue.field.split('.');
    if (split.length > 1) {
      const value = item[split[0]];
      if (value) {
        finalValue = value[split[1]];
      }
    } else {
      finalValue = item[tableValue.field];
    }

    if (tableValue.formatEnum) {
      if (tableValue.formatEnum === FormatEnum.PHONE_MASK) {
        return this.maskPipe.transform(finalValue, this.translate.instant(tableValue.formatEnum));
      } else if (tableValue.formatEnum === FormatEnum.YES_NO) {
        return finalValue ? this.translate.instant('yes') : this.translate.instant('no');
      }
    }

    return finalValue;
  }

  updateItems(page) {
    if (!this.controlCurrentPage) {
      this.currentPage = page;
    }
    this.reloadItems.emit(page - 1);
  }

  onClickItem(item) {
    this.clickItem.emit(item);
  }
}
