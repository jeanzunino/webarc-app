export class ButtonGroupValues {
  buttonId: string;
  buttonName: string;
}

export class ButtonGroup {
  private values: ButtonGroupValues[] = [];

  set(buttonId: string, buttonName: string): ButtonGroup {
    const buttonGroupValues = new ButtonGroupValues();
    buttonGroupValues.buttonId = buttonId;
    buttonGroupValues.buttonName = buttonName;
    this.values.push(buttonGroupValues);
    return this;
  }

  get(): ButtonGroupValues[] {
    return this.values;
  }
}
