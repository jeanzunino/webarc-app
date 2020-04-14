import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.scss']
})
export class PanelComponent implements OnInit {

  @Input() panelTitle: string;
  isTheFilterPanelExpanded = true;

  constructor() { }

  ngOnInit(): void {
  }

  updateExpandedFlagPanel() {
    this.isTheFilterPanelExpanded = !this.isTheFilterPanelExpanded;
  }

}
