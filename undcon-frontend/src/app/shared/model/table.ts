export class TableFieldCustomizationEmitter {
  item: any[] = [];
  field: string;

  constructor(item, field) {
    this.item = item;
    this.field = field;
  }
}

export class TableValues {
  field: string;
  columnTitle: string;
}

export class Table {
  private values: TableValues[] = [];

  set(field: string, columnTitle: string): Table {
    let tableValues = new TableValues();
    tableValues.field = field;
    tableValues.columnTitle = columnTitle;
    this.values.push(tableValues);
    return this;
  }

  get(): TableValues[] {
    return this.values;
  }
}