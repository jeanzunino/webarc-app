import { TranslateService } from '@ngx-translate/core';

import { SharedInjector } from "@shared/shared.module";

const translate = SharedInjector.get(TranslateService);

export const getTranslate = (key, params = null) => {
  if (params) {
    return translate.instant(key, params)
  } else {
    return translate.instant(key);
  }
}