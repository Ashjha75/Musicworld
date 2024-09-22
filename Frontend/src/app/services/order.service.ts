import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Order } from '../interfaces/order';
import { OrderResponse } from '../interfaces/response/orderresponse';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "orders";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(auth: Auth,): Observable<Order[]> {
    return this.http.get<Order[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth, orderId: number): Observable<Order> {
    return this.http.get<Order>(this.root_url + this.addressAPI + '/' + orderId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public create(auth: Auth, address: string): Observable<OrderResponse> {
    return this.http.post<OrderResponse>(this.root_url + this.addressAPI, address, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, orderId: number): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + orderId, { headers: this.utilityService.cloneHeader(auth) })
  }
}
