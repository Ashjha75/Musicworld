import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Product } from '../../interfaces/product';
import { ProductService } from '../../services/product.service';
import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';

@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public products: Product[] = [];
  public categories: Category[] = [];

  constructor(private productService : ProductService,
              private categoryService : CategoryService) {
    this.getMatching();
  }

  ngOnInit(): void {
  }

  public getMatching(): void{
    this.productService.getAll().subscribe(
      (responseprod: Product[]) => {
        this.products = responseprod;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
    this.categoryService.getAll().subscribe(
      (responsecat: Category[]) => {
        this.categories = responsecat;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

}
