import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';



@NgModule({
  declarations: [BreadcrumbComponent],
  exports: [
    BreadcrumbComponent
  ],
  imports: [
    CommonModule,
    NzBreadCrumbModule
  ]
})
export class SharingModule { }
