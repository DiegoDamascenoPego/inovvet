import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-header-pesquisa-form',
  templateUrl: './header-pesquisa-form.component.html',
})
export class HeaderPesquisaFormComponent implements OnInit {

  @Input() titulo: string;
  @Input() showPesquisa: boolean;
  
  constructor() { }

  ngOnInit() {
  }

}
