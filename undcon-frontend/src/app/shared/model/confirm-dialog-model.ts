export class ConfirmDialogModel {

  constructor(msg: string, modalTitle?: string, labelBtnConfirm?: string, labelBtnCancel?: string) {
    this.msg = msg;
    this.modalTitle = modalTitle ? modalTitle : 'Confirmação';
    this.labelBtnConfirm = labelBtnConfirm ? labelBtnConfirm : 'Confirmar';
    this.labelBtnCancel = labelBtnCancel ? labelBtnCancel : 'Cancelar';
  }

  msg;
  modalTitle;
  labelBtnConfirm;
  labelBtnCancel;
}
