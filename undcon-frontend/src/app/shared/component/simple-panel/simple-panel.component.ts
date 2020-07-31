import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-simple-panel',
  templateUrl: './simple-panel.component.html'
})
export class SimplePanelComponent implements OnInit {
  @Input() panelTitle: string;
  @Input() showHeader = false;
  @Input() showFooter = false;

  constructor() {}

  ngOnInit(): void { }
}
