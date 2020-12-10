import { TranslateService } from '@ngx-translate/core';
import { MDBModalService } from 'angular-bootstrap-md';

import { SharedInjector } from '@shared/shared.module';
import { Entity } from '@model/entity';
import { QueryFilterEnum } from '@app/core/enum/query-filter';
import { FormatEnum } from '@app/core/enum/format-enum';
import { ItemType } from '@app/core/enum/item-type';
import { ConfirmDialogModel } from '../model/confirm-dialog-model';
import { ConfirmDialogComponent } from '../component/confirm-dialog/confirm-dialog.component';

const translate = SharedInjector.get(TranslateService);

const modalService = SharedInjector.get(MDBModalService);

export const getTranslate = (key, params = null) => {
  if (params) {
    return translate.instant(key, params);
  } else {
    return translate.instant(key);
  }
};

export const openDialog = (item: Entity, obj: any) => {
  document.body.style.overflow = 'hidden';
  return modalService.show(obj, {
    backdrop: true,
    keyboard: true,
    focus: true,
    show: false,
    ignoreBackdropClick: true,
    class: 'modal-dialog-centered',
    containerClass: '',
    animated: true,
    data: {
      content: item
    }
  });
};

export const openSimpleDialog = (item: any, obj: any) => {
  document.body.style.overflow = 'hidden';
  return modalService.show(obj, {
    backdrop: true,
    keyboard: true,
    focus: true,
    show: false,
    ignoreBackdropClick: true,
    class: 'modal-dialog-centered',
    containerClass: '',
    animated: true,
    data: {
      content: item
    }
  });
};

export const openConfimDialog = (confirmDialogModel: ConfirmDialogModel) => {
  document.body.style.overflow = 'hidden';
  return modalService.show(ConfirmDialogComponent, {
    backdrop: true,
    keyboard: true,
    focus: true,
    show: false,
    ignoreBackdropClick: true,
    class: 'modal-dialog-centered',
    containerClass: '',
    animated: true,
    data: {
      content: confirmDialogModel
    }
  });
};

export const getQueryFilter = (field, operation: QueryFilterEnum) => {
  return `${field}${operation}`;
};

export const getPhoneMask = (formatEnum: FormatEnum) => {
  return getTranslate(formatEnum);
};

export const getEnumTranslation = (type: ItemType) => {
  return getTranslate("enums.item-type." + type);
};
