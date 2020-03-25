import { Component, OnInit } from '@angular/core';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { ProviderService } from '@service/provider/provider.service';
import { Provider } from '@model/provider';

@Component({
  selector: 'app-provider',
  templateUrl: './provider.component.html',
  styleUrls: ['./provider.component.scss']
})
export class ProviderComponent implements OnInit {

  private ngUnsubscribe = new Subject();

  constructor(private providerService: ProviderService) { }

  items: Provider[];

  ngOnInit() {
    this.providerService.getProviders()
    .pipe(takeUntil(this.ngUnsubscribe))
    .subscribe(items => {
      this.items = items;
    });
  }

  onClickItem(item) {
    console.log(item)
  }
}
