import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { ServiceTypeService } from '@service/service-type/service-type.service';
import { ServiceType } from '@model/service-type';

@Component({
  selector: 'app-service-type',
  templateUrl: './service-type.component.html',
  styleUrls: ['./service-type.component.scss']
})
export class ServiceTypeComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private serviceTypeService: ServiceTypeService) { }

  items: ServiceType[];

  ngOnInit() {
    this.serviceTypeService.getServiceTypes()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
    console.log(item)
  }
}
