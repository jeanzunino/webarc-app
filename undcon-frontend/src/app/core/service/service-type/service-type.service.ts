
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ServiceType } from '@model/service-type';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceTypeService extends EntityService<ServiceType> {

  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'serviceTypes')
  }

}