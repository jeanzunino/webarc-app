import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '@app/core/service/customer/customer.service';
import { Customer } from '@app/core/model/customer';
import { Page } from '@app/core/model/page';
import { Employee } from '@app/core/model/employee';

@Component({
  selector: 'app-sale-detail',
  templateUrl: './sale-detail.component.html',
  styleUrls: ['./sale-detail.component.scss']
})
export class SaleDetailComponent implements OnInit {

  constructor(private router: Router, private rt: ActivatedRoute, private cs: CustomerService) { }

  data: Customer[];

  async ngOnInit() {
    this.data = ((await this.cs.getAll().toPromise()) as Page<
      Customer
    >).content
  }

  goBack() {
    const previousRoute = "../";
    this.router.navigate([previousRoute], { relativeTo: this.rt.parent });
  }
}
