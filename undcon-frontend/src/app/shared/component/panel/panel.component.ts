import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";

@Component({
  selector: "app-panel",
  templateUrl: "./panel.component.html"
})
export class PanelComponent implements OnInit {
  @Output() search: EventEmitter<any> = new EventEmitter();
  @Output() clear: EventEmitter<any> = new EventEmitter();
  @Input() panelTitle: string;
  @Input() showDefaultSearchButtons = true;
  isTheFilterPanelExpanded = true;

  constructor() {}

  ngOnInit(): void {}

  updateExpandedFlagPanel() {
    this.isTheFilterPanelExpanded = !this.isTheFilterPanelExpanded;
  }

  onSearch() {
    this.search.emit();
  }

  onClear() {
    this.clear.emit();
  }
}
