import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-header-cadastro-form',
  templateUrl: './header-cadastro-form.component.html',
  styleUrls: ['./header-cadastro-form.component.scss']
})
export class HeaderCadastroFormComponent {

  @Input() titulo: string;
  @Output() closeForm = new EventEmitter();

  constructor() { }

  onVoltar() {
    this.closeForm.emit();
  }


}
