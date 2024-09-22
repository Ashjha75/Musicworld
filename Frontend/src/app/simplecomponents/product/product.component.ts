import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Product } from '../../interfaces/product';
import { ProductService } from '../../services/product.service';

@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  public products: Product[] = [];
  public product = <Product>{ };

  constructor(private productService : ProductService) {
  }

  ngOnInit(): void {
  }

  public getById(id: number): void{
    this.productService.getById(id).subscribe(
      (response: Product) => {
        this.product = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
