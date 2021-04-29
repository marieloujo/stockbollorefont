import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';

@Component({
  selector: 'app-gamme',
  templateUrl: './gamme.component.html',
  styleUrls: ['./gamme.component.css']
})
export class GammeComponent implements OnInit {

  constructor(
    private behaviorService: BehaviorService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Matériel', 'Gamme']);
  }

}
