import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Provider } from '@model/provider';
import { EntityService } from '@service/entity/entity.service';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProviderService extends EntityService<Provider> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService) {
    super(http, storageService, 'providers')
  }

}
