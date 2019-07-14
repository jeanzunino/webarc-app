import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ServiceTypesService } from '../../services/service-types.service';
import { Service } from '../../model/service';

@Component({
  selector: 'app-service-type-detail',
  templateUrl: './service-type-detail.component.html',
  styleUrls: ['./service-type-detail.component.css']
})
export class ServiceTypeDetailComponent implements OnInit {

  @Input() item: Service;

  constructor(
    private route: ActivatedRoute,
    private service: ServiceTypesService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.get();
  }

  get(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.service.get(id)
      .subscribe(item => this.item = item);
  }

  goBack(): void {
    this.location.back();
  }

}
