import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-forbidden',
  templateUrl: './page-forbidden.component.html',
  styleUrls: ['./page-forbidden.component.scss']
})
export class PageForbiddenComponent {

  public openInstagram() {
    window.open('https://instagram.com/undconsolutions?igshid=bvu7warltias');
  }
}
