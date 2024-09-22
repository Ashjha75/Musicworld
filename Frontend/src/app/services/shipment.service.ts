import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Shipment } from '../interfaces/shipment';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class ShipmentService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "shipments";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(auth: Auth): Observable<Shipment[]> {
    return this.http.get<Shipment[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth, shipmentId: number): Observable<Shipment> {
    return this.http.get<Shipment>(this.root_url + this.addressAPI + '/' + shipmentId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public save(auth: Auth, orderId: number): Observable<Shipment> {
    return this.http.post<Shipment>(this.root_url + this.addressAPI + '/' + orderId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, orderId: number): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + orderId, { headers: this.utilityService.cloneHeader(auth) })
  }
}
