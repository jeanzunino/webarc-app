export class InstallmentDialog {

  constructor(valueToInstallments = 0, numberInstallments = 12, installmentsDefault = 6) {
    this.valueToInstallments = valueToInstallments;
    this.numberInstallments = numberInstallments;
    this.installmentsDefault = installmentsDefault;
  }

  numberInstallments: number;
  valueToInstallments: number;
  installmentsDefault: number;
}
