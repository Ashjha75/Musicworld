import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Auth } from '../../interfaces/utility/auth';
import { Cart } from '../../interfaces/cart';
import { Product } from '../../interfaces/product';
import { ProductService } from '../../services/product.service';
import { Producer } from '../../interfaces/producer';
import { ProducerService } from '../../services/producer.service';
import { CartService } from '../../services/cart.service';
import { DataService } from '../../services/data.service';
import { UtilityService } from '../../services/utility.service';

import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-best',
  templateUrl: './best.component.html',
  styleUrls: ['./best.component.css']
})
export class BestComponent implements OnInit {

  public products: Product[] = [];
  public productsCart: Product[] = [];
  public producers: Producer[] = [];
  public logged: boolean;

  constructor(private productService: ProductService,
    private producerService: ProducerService,
    private cartService: CartService,
    private categoryService: CategoryService,
    private dataService: DataService,
    private utilityService: UtilityService,
    private router: Router) {
    this.getBestProducts();
    this.getBestProducers();
    this.logged = false;
  }

  ngOnInit(): void {
    this.logged = this.dataService.getLogStatus().loggedIn;
    if (this.logged) {
      this.getCart(this.dataService.getAuth());
    }
  }

  public quantityArray(length: number): Array<number> {
    return this.utilityService.quantityArray(length);
  }

  public visitstore(mail: String): void {
    this.router.navigate(['/producer/' + mail]);
  }

  public getBestProducts(): void {
    this.productService.getBest().subscribe(
      (response: Product[]) => {         
        response.forEach(
          product => this.categoryService.getById(product.category)
            .subscribe(category => product.categoryName = category.name)
        )      
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getBestProducers(): void {
    this.producerService.getBest(10).subscribe(
      (response: Producer[]) => {
        this.producers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCart(auth: Auth): void {
    this.cartService.getCart(auth).subscribe(
      (response: Cart[]) => {
        response.forEach(cart => this.productService.getById(cart.productId).subscribe(product => this.productsCart.push(product)));
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
