import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

import { Product } from '../../interfaces/product';
import { Order } from '../../interfaces/order';
import { Auth } from '../../interfaces/utility/auth';

import { ProductService } from '../../services/product.service';
import { DataService } from '../../services/data.service';
import { OrderService } from 'src/app/services/order.service';


@Injectable({
  providedIn : 'root'
})
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  public allOrders: Order[] = [];
  public lastOrder = <Order>{ };
  public lastProductsOrdered: Product[] = [];
  private auth = <Auth>{ };

  constructor(private productService : ProductService,
              private orderService: OrderService,
              private dataService: DataService) { }

  ngOnInit(): void {
    this.init();
    this.auth = this.dataService.getAuth();
  }

  private init(): void{
    this.orderService.getAll(this.auth).subscribe(orders => this.allOrders = orders);
    this.lastOrder = this.allOrders[this.allOrders.length - 1];
  }
}
