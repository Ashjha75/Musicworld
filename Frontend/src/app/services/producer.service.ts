import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Producer } from '../interfaces/producer';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class ProducerService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "producers";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(auth: Auth): Observable<Producer[]> {
    return this.http.get<Producer[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth, producerId: string): Observable<Producer> {
    return this.http.get<Producer>(this.root_url + this.addressAPI + '/' + producerId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public save(auth: Auth, producer: Producer): Observable<Producer> {
    return this.http.post<Producer>(this.root_url + this.addressAPI, producer, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, producerId: string): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + producerId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public getBest(limit: number): Observable<Producer[]> {
    return this.http.get<Producer[]>(this.root_url + this.addressAPI + '/best/' + limit)
  }
}
