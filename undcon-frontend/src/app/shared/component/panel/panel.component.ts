import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.scss']
})
export class PanelComponent implements OnInit {

  @Output() onSearch: EventEmitter<any> = new EventEmitter();
  @Input() panelTitle: string;
  @Input() showSearchButton: boolean = true;
  isTheFilterPanelExpanded = true;

  constructor() { }

  ngOnInit(): void {
  }

  updateExpandedFlagPanel() {
    this.isTheFilterPanelExpanded = !this.isTheFilterPanelExpanded;
  }

  onClickSearch() {
    this.onSearch.emit();
  }
}
