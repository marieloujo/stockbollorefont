import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BehaviorService {

  //private isLeftSideDisplaying: BehaviorSubject<string>;
  private breadcrumbItems: BehaviorSubject<string[]>;

  constructor() {
    //this.isLeftSideDisplaying = new BehaviorSubject<string>('none');
    this.breadcrumbItems = new BehaviorSubject<string[]>(['Accueil']);
  }

  public setBreadcrumbItems(param: string[]): void {
    this.breadcrumbItems.next(param);
  }

  public getBreadcrumbItems(): Observable<string[]> {
    return this.breadcrumbItems.asObservable();
  }

}
