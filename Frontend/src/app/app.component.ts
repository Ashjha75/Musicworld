import { Component, OnInit } from '@angular/core';

import { DataService } from './services/data.service';
import { LogStatus } from './interfaces/utility/logstatus';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string = "MusicStore"
  logStatus: LogStatus = {
    loggedIn: false
  };


  constructor(private dataService: DataService) { }

  ngOnInit() {
  }
}
