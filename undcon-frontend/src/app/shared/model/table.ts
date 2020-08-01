import { FormatEnum } from '@app/core/enum/format-enum';

export class TableValues {
  field: string;
  columnTitle: string;
  formatEnum: string;

}

export class Table {
  private values: TableValues[] = [];

  set(field: string, columnTitle: string, formatEnum: FormatEnum = null): Table {
    const tableValues = new TableValues();
    tableValues.field = field;
    tableValues.columnTitle = columnTitle;
    tableValues.formatEnum = formatEnum;
    this.values.push(tableValues);
    return this;
  }

  get(): TableValues[] {
    return this.values;
  }
}
