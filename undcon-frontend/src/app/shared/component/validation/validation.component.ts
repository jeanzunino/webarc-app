import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "app-validation",
  templateUrl: "./validation.component.html"
})
export class ValidationComponent implements OnInit {
  @Input() form;
  @Input() validMessage: string = "";
  @Input() invalidMessage: string = "";

  constructor() {}

  ngOnInit(): void {}
}
