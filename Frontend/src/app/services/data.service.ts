import { Injectable } from '@angular/core';

import { CartService } from '../services/cart.service';
import { OrderService } from '../services/order.service';

import { LogStatus } from '../interfaces/utility/logstatus';
import { Auth } from '../interfaces/utility/auth';

import { Router } from '@angular/router';

@Injectable({
  providedIn : 'root'
})
export class DataService{

  private auth = <Auth>{ };
  private logStatus = <LogStatus>{ };

  constructor(private cartService : CartService,
              private orderService : OrderService,
              private router : Router){
    this.initAuth();
    this.setLogStatus(false);
  }

  public getAuth(): Auth{
    return this.auth;
  }

  public setAuth(mail: string, password: string): void{
    this.auth.mail = mail;
    this.auth.password = password;
  }

  public initAuth(): void{
    this.auth.mail = '';
    this.auth.password = '';
  }

  public getLogStatus(): LogStatus{
    return this.logStatus;
  }

  public setLogStatus(status: boolean): void{
    this.logStatus.loggedIn = status;
  }
}
