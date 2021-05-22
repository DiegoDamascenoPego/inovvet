import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-column',
  templateUrl: './column.component.html',
  styleUrls: ['./column.component.css']
})
export class ColumnComponent implements OnInit {

  @Input() definition: string;
  @Input() header: string;
  @Input() entity: any;
  @Input() value: any;

  constructor() { }

  ngOnInit(): void {
  }

}
