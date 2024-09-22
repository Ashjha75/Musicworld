import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Customer } from '../interfaces/customer';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "customers";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(auth: Auth): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth): Observable<Customer> {
    return this.http.get<Customer>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) })
  }

  public create(auth: Auth, customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.root_url + this.addressAPI, customer, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, customerId: number): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + customerId, { headers: this.utilityService.cloneHeader(auth) })
  }
}
