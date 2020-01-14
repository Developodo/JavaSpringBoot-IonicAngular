import { Injectable } from '@angular/core';
import { HTTP } from '@ionic-native/http/ngx';
import { environment } from '../../environments/environment';
import { Item } from './Item';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HTTP) {}
  private prepareHeader(): any {
    return {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json'
    };
  }

  public getItem(id?: number | string): Promise<Item[] | null> {
    let endpoint = environment.endopoint + environment.apiItem;
    if (id) {
      endpoint += id;
    }
    return new Promise((resolve, reject) => {
      this.http
        .get(endpoint, {}, this.prepareHeader())
        .then(d => {
          if (d) {
            resolve(JSON.parse(d.data));
          } else {
            resolve(null);
          }
        })
        .catch(err => reject(err));
    });
  }
  public searchByTitle(value: string): Promise<Item[] | null>  {
    return this.getItem('search/' + value);
  }
  public removeItem(item: any): Promise<void> {
    const id: number = item.id ? item.id : item;
    let endpoint = environment.endopoint + environment.apiItem + id;
    return new Promise((resolve, reject) => {
      this.http
        .delete(endpoint, {}, this.prepareHeader())
        .then(d => {
          resolve();
        })
        .catch(err => reject(err));
    });
  }
  public updateItem(item: Item): Promise<void> {
    const endpoint = environment.endopoint + environment.apiItem;
    return new Promise((resolve, reject) => {
      if (item) {
        this.http.setDataSerializer('json'); //send body as json, needed
        this.http
          .post(endpoint, item, this.prepareHeader())
          .then(d => {
            resolve();
          })
          .catch(err => reject(err));
      } else {
        reject('No existe item');
      }
    });
  }
}
