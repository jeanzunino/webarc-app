import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { EntityService } from '@service/entity/entity.service';
import { StorageService } from '@service/storage/storage.service';
import { Income } from '@app/core/model/income';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IncomeService extends EntityService<Income> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, 'incomes');
  }

  public updateStatus(item: Income): Observable<Income> {
    return this.putCustomUrl(`${this.baseUrl}/${this.entityUrl}/${item.id}/updateStatus`, item) as Observable<Income>;
  }
}
