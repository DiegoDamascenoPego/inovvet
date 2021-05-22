import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { InputValidatorsService } from '../input-validators.service';

@Component({
  selector: 'app-error-detail-form',
  templateUrl: './error-detail-form.component.html',
  styleUrls: ['./error-detail-form.component.css']
})
export class ErrorDetailFormComponent implements OnInit {

@Input() descricao = '';
@Input() error: Validators;

  constructor() { }

  ngOnInit(): void {
  }

}
