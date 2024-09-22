import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';

import { Product } from '../../interfaces/product';
import { ProductService } from '../../services/product.service';
import { Producer } from '../../interfaces/producer';
import { ProducerService } from '../../services/producer.service';
import { Category } from '../../interfaces/category';
import { CategoryService } from '../../services/category.service';

import { Router } from '@angular/router';
import { DataService } from 'src/app/services/data.service';

@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-producer',
  templateUrl: './producer.component.html',
  styleUrls: ['./producer.component.css']
})
export class ProducerComponent implements OnInit {

  public products: Product[] = [];
  public categories: Category[] = [];
  public producer = <Producer>{};
  public mail: string = "";

  constructor(private productService : ProductService,
              private categoryService : CategoryService,
              private producerService : ProducerService,
              private dataService : DataService,
              private router: Router) {}

  ngOnInit(): void {
    this.mail = this.router.url.replace('producer/','');
    this.getProducerById(this.mail);
    this.getProducts(this.mail);
    this.getCategories(this.mail);
  }

  public getProducerById(mail: string): void{
    this.producerService.getById(this.dataService.getAuth(), mail).subscribe(
      (response: Producer) => {
        this.producer = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getProducts(mail: String): void{
    this.productService.getByProducer(mail).subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCategories(mail: String): void{
    this.categoryService.getByProducer(mail).subscribe(
      (response: Category[]) => {
        this.categories = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
