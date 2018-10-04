import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  activeTab: string = 'appraisal';

  constructor(private router: Router) {}

  ngOnInit() {
    let uriArray = this.router.url.split("/");
    this.activeTab = uriArray[uriArray.length - 1];
  }

  getActiveTab(tab: string):void {
    this.activeTab = tab;
  }

}
