import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.css']
})
export class BreadcrumbComponent implements OnInit {

  breadcrumbItems: string[] = [];

  constructor(
    private behaviorService: BehaviorService
  ) { }

  ngOnInit(): void {
    this.behaviorService.getBreadcrumbItems().subscribe((data) => {
      this.breadcrumbItems = data;
    });
  }

}
