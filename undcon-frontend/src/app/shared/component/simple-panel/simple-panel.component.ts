import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-simple-panel',
  templateUrl: './simple-panel.component.html'
})
export class SimplePanelComponent implements OnInit {
  @Input() panelTitle: string;
  @Input() showHeader = false;
  @Input() showFooter = false;

  iconFinal = '';
  @Input() set icon(icon: string) {
    this.iconFinal = icon;
  }

  iconColorFinal = '';
  @Input() set iconColor(iconColor: string) {
    this.iconColorFinal = iconColor;
  }

  headerColorFinal = 'secondary-system-color';
  @Input() set headerColor(color: string) {
    this.headerColorFinal = color;
  }

  @Input() panelTitleColor = 'text-light';

  constructor() {}

  ngOnInit(): void { }
}
