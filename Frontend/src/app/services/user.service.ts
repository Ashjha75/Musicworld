import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Auth } from '../interfaces/utility/auth';
import { UserResponse } from '../interfaces/response/userresponse';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private root_url = environment.apiBaseUrl;

  private addressAPI: string = "users";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(auth: Auth): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(this.root_url + this.addressAPI + '/all', { headers: this.utilityService.cloneHeader(auth) })
  }

  public getById(auth: Auth): Observable<UserResponse> {
    return this.http.get<UserResponse>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) })
  }

  public save(auth: Auth): Observable<UserResponse> {
    return this.http.post<UserResponse>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) });
  }

  public delete(auth: Auth): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI, { headers: this.utilityService.cloneHeader(auth) })
  }
}
