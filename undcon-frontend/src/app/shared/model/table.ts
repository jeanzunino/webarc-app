export class TableValues {
  field: string;
  columnTitle: string;
  mask: string;
}

export class Table {
  private values: TableValues[] = [];

  set(field: string, columnTitle: string, mask: string = null): Table {
    const tableValues = new TableValues();
    tableValues.field = field;
    tableValues.columnTitle = columnTitle;
    tableValues.mask = mask;
    this.values.push(tableValues);
    return this;
  }

  get(): TableValues[] {
    return this.values;
  }
}
