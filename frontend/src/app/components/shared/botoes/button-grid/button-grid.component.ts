import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-button-grid',
  templateUrl: './button-grid.component.html',
  styleUrls: ['./button-grid.component.css']
})
export class ButtonGridComponent implements OnInit {

  @Input() name = ''
  @Input() type = ''
  @Input() styles = ''
  @Input() icon = ''
  @Output() Click = new EventEmitter();


  constructor() { }

  ngOnInit(): void {
  }

  onClick(){
    this.Click.emit();
  }

}
