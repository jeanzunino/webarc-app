import { TranslateService } from '@ngx-translate/core';
import { MDBModalService } from 'angular-bootstrap-md';

import { SharedInjector } from '@shared/shared.module';
import { Entity } from '@model/entity';

const translate = SharedInjector.get(TranslateService);

const modalService = SharedInjector.get(MDBModalService);

export const getTranslate = (key, params = null) => {
  if (params) {
    return translate.instant(key, params);
  } else {
    return translate.instant(key);
  }
};

export const openDialog = (item: Entity, obj: Object) => {
  return modalService.show(obj, {
    backdrop: true,
    keyboard: true,
    focus: true,
    show: false,
    ignoreBackdropClick: false,
    class: 'modal-dialog-centered',
    containerClass: '',
    animated: true,
    data: {
      content: item
    }
  });
}
