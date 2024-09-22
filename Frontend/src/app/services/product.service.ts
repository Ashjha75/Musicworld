import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Product } from '../interfaces/product';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private root_url = environment.apiBaseUrl;
  private addressAPI: string = "products";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getByCategory(categoryId: String): Observable<Product[]> {
    return this.http.get<Product[]>(this.root_url + this.addressAPI + '/category/' + categoryId);
  }

  public getByProducer(mail: String): Observable<Product[]> {
    return this.http.get<Product[]>(this.root_url + this.addressAPI + '/producer/' + mail);
  }

  public getBest(): Observable<Product[]> {
    return this.http.get<Product[]>(this.root_url + this.addressAPI + '/best');
  }

  public getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.root_url + this.addressAPI + '/all');
  }

  public getById(productId: number): Observable<Product> {
    return this.http.get<Product>(this.root_url + this.addressAPI + '/product/' + productId);
  }

  public createAsProducer(mail: string, product: Product): Observable<Product> {
    return this.http.post<Product>(this.root_url + this.addressAPI + '/producer/' + mail, product);
  }

  public createAsAdmin(product: Product): Observable<Product> {
    return this.http.post<Product>(this.root_url + this.addressAPI + '/admin', product);
  }

  public delete(auth: Auth, productId: number): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + productId, { headers: this.utilityService.cloneHeader(auth) })
  }
}
