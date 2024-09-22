import { Component, OnInit } from '@angular/core';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
@Component({
    selector: 'app-error',
    templateUrl: './error.component.html',
    styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

    constructor() {
    }

    ngOnInit(): void {
    }
}
