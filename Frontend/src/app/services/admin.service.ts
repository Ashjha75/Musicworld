import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Admin } from '../interfaces/admin';

import { environment } from 'src/environments/environment';
import { Auth } from '../interfaces/utility/auth';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "admins";

  constructor(private http: HttpClient,
              private utilityService: UtilityService) { }

  public getAll(auth: Auth): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth, adminId: string): Observable<Admin> {
    return this.http.get<Admin>(this.root_url + this.addressAPI + '/' + adminId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public save(auth: Auth, admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(this.root_url + this.addressAPI, admin, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, adminId: string): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + adminId, { headers: this.utilityService.cloneHeader(auth) })
  }
}
