import { Component, ViewChild } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Item } from '../services/Item';
import { UiService } from '../services/ui.service';
import { FormPage } from '../form/form.page';
import { IonSearchbar } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss']
})
export class HomePage {
  @ViewChild('input', {static: false}) myInput: IonSearchbar ;
  public listado: Item[];
  constructor(private api: ApiService, private ui: UiService) {}
  async ionViewDidEnter() {
    await this.loadAll();
  }

  public async loadAll() {
    await this.ui.showLoading();
    this.api
      .getItem()
      .then(d => (this.listado = d))
      .catch(async err => await this.ui.showToast(err.error))
      .finally(async () => {
        await this.ui.hideLoading();
      });
  }
  public async buscaItem($event) {
    let value = $event.detail.value;
    value = value.trim();
    if (value !== '') {
      //await this.ui.showLoading();
      this.api.searchByTitle(value)
      .then(d => {
        this.listado = d;
      })
      .catch(async err => await this.ui.showToast(err.error))
      .finally(async () => {
        //await this.ui.hideLoading();
        //this.myInput.setFocus();
      });
    } else {
      await this.loadAll();
    }
  }
  public async removeItem(item: Item) {
    await this.ui.showLoading();
    this.api
      .removeItem(item)
      .then(async d => await this.loadAll())
      .catch(async err => await this.ui.showToast(err.error))
      .finally(async () => {
        await this.ui.hideLoading();
      });
  }

  public async addItem() {
    const itemToBeUpdated = await this.ui.showModal(FormPage, { item: {} });
    try {
      if (itemToBeUpdated.data) {
        // si no cierra
        await this.ui.showLoading();
        await this.api.updateItem(itemToBeUpdated.data);
        await this.loadAll();
      }
    } catch (err) {
      await this.ui.hideLoading();
      await this.ui.showToast(err.error);
    }
  }
  // tslint:disable-next-line: variable-name
  public async editItem(_item: Item) {
    const itemToBeUpdated = await this.ui.showModal(FormPage, { item : _item });
    try {
      if (itemToBeUpdated.data) {
        // si no cierra
        await this.ui.showLoading();
        await this.api.updateItem(itemToBeUpdated.data);
        await this.loadAll();
      }
    } catch (err) {
      await this.ui.hideLoading();
      await this.ui.showToast(err.error);
    }
  }
}
