import { Injectable } from '@angular/core';

import { HttpHeaders } from '@angular/common/http';
import { } from '@angular/common/http';

import { Auth } from '../interfaces/utility/auth';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {
  private httpOptions = {
    headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('userRequest', '{}')
  };

  constructor() { }

  public quantityArray(length: number): Array<number> {
    if (length >= 0) {
      length -= 1;
      let arr = new Array(length);
      for (let i = 0; i < length; ++i) {
        arr[i] = i + 2;
      }
      return arr;
    }
    return [];
  }

  public cloneHeader(auth: Auth): HttpHeaders {
    return this.httpOptions.headers.set('userRequest', JSON.stringify(auth).replace('^\^', ''));
  }

  public getOptions(): HttpHeaders {
    return this.httpOptions.headers;
  }
}
