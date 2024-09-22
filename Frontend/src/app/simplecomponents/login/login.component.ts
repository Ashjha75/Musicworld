import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { HttpErrorResponse } from '@angular/common/http';

import { User } from '../../interfaces/user';
import { UserResponse } from '../../interfaces/response/userresponse';
import { UserService } from '../../services/user.service';
import { DataService } from '../../services/data.service';
import { Auth } from 'src/app/interfaces/utility/auth';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
    private dataService: DataService,
    private router: Router) { }

  ngOnInit(): void {
    this.dataService.setLogStatus(false);
    this.dataService.setAuth('', '');
  }

  public login(): void {
    let auth: Auth = {
      mail: (<HTMLInputElement>document.getElementById("username"))?.value,
      password: (<HTMLInputElement>document.getElementById("password"))?.value
    };
    this.userService.getById(auth).subscribe(
      (response: UserResponse) => {
        this.dataService.setAuth(response.mail, auth.password);
        this.dataService.setLogStatus(true);
        this.router.navigate(['/']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        this.router.navigate(['/login']);
      }
    );
  }
}
