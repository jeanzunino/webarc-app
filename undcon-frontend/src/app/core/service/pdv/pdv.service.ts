import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Pdv } from '@model/pdv';
import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { PdvResume } from '@app/core/model/pdv-resume';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PdvService extends EntityService<Pdv> {

  constructor(protected http: HttpClient,
              protected storageService: StorageService) {
    super(http, storageService, 'pdvs')
  }

  public getResumePdv(): Observable<PdvResume> {
    return this.getCustomUrl(`${this.baseUrl}/${this.entityUrl}/resume`) as Observable<PdvResume>;
  }
}
