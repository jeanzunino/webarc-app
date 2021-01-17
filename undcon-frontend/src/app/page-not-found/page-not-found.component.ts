import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-page-not-found",
  templateUrl: "./page-not-found.component.html",
  styleUrls: ['./page-not-found.component.scss'],
})
export class PageNotFoundComponent {

  public openInstagram() {
    window.open('https://instagram.com/undconsolutions?igshid=bvu7warltias');
  }
}
