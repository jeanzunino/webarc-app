import { InstallmentDto } from './../dto/Installment-dto';
import { CloseDialogValues } from './close-dialog-values';

export class CloseDialogInstallmentValeus extends CloseDialogValues {
  installmentDtos: InstallmentDto[] = [];
}
