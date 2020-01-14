import { Component, OnInit, Input } from '@angular/core';
import { ModalController, NavParams } from '@ionic/angular';
import { Item } from '../services/Item';

import {
  Validators,
  FormBuilder,
  FormGroup,
  FormControl
} from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.page.html',
  styleUrls: ['./form.page.scss']
})
export class FormPage implements OnInit {
  private item: Item;

  private mode: string;
  private form: FormGroup;

  constructor(
    private modal: ModalController,
    private formBuilder: FormBuilder,
    navParams: NavParams
  ) {
    this.item = navParams.get('item');
    if (this.item && this.item.id) {
      this.mode = 'Editing';
    } else {
      this.mode = 'Creating';
      this.item = {
        id: '', // for autoincrement
        title: '',
        description: ''
      };
    }

    this.form = this.formBuilder.group({
      id: new FormControl(this.item.id),
      title: new FormControl(
        this.item.title,
        Validators.compose([Validators.required, Validators.maxLength(128)])
      ),
      description: new FormControl(
        this.item.description,
        Validators.compose([Validators.maxLength(256)])
      )
    });
    /*this.form.valueChanges.subscribe(d=>{
      console.log(this.errorControl);
    })*/
  }

  ngOnInit() {}
  get errorControl() {
    return this.form.controls;
  }
  get errorControlTitle() {
    if (this.errorControl.title.status === 'INVALID') {
      if (this.errorControl.title.errors.required) {
        return 'Campo title requerido';
      }
      if (this.errorControl.title.errors.maxlength) {
        return 'La longitud máxima de title es de 128 carácteres';
      }
    }
  }
  get errorControlDescription() {
    if (this.errorControl.description.status === 'INVALID') {
      if (this.errorControl.description.errors.maxlength) {
        return 'La longitud máxima de title es de 256 carácteres';
      }
    }
  }
  submitForm() {
    this.dismiss(this.form.value);
  }
  public dismiss(item: Item) {
    this.modal.dismiss(item);
  }
}
