import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Auth } from '../interfaces/utility/auth';
import { Cart } from '../interfaces/cart';
import { CartRequest } from '../interfaces/cartrequest';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "carts";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getCart(auth: Auth): Observable<Cart[]> {
    return this.http.get<Cart[]>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) })
  }

  public save(auth: Auth, cartRequest: CartRequest): Observable<void> {
    return this.http.post<void>(this.root_url + this.addressAPI, cartRequest, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) })
  }
}
