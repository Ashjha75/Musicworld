import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { Category } from '../interfaces/category';
import { Auth } from '../interfaces/utility/auth';

import { environment } from 'src/environments/environment';
import { UtilityService } from './utility.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private root_url = environment.apiBaseUrl;
  private addressAPI: string = "categories";

  constructor(private http: HttpClient,
    private utilityService: UtilityService) { }

  public getAll(): Observable<Category[]> {
    return this.http.get<Category[]>(this.root_url + this.addressAPI + '/all')
  }

  public getById(categoryId: number): Observable<Category> {
    return this.http.get<Category>(this.root_url + this.addressAPI + '/' + categoryId)
  }

  public update(auth: Auth, category: Category): Observable<Category> {
    return this.http.post<Category>(this.root_url + this.addressAPI, category, { headers: this.utilityService.cloneHeader(auth) })
  }

  public delete(auth: Auth, categoryId: number): Observable<void> {
    return this.http.delete<void>(this.root_url + this.addressAPI + '/' + categoryId, { headers: this.utilityService.cloneHeader(auth) })
  }

  public getByProducer(producerId: String): Observable<Category[]> {
    return this.http.get<Category[]>(this.root_url + this.addressAPI + '/' + producerId + this.addressAPI);
  }
}
