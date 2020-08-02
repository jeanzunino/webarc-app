import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

import { ProductCategory } from "@model/product-category";
import { EntityService } from "@service/entity/entity.service";
import { StorageService } from "@service/storage/storage.service";

@Injectable({
  providedIn: "root",
})
export class ProductCategoryService extends EntityService<ProductCategory> {
  constructor(
    protected http: HttpClient,
    protected storageService: StorageService
  ) {
    super(http, storageService, "productCategories");
  }
}
