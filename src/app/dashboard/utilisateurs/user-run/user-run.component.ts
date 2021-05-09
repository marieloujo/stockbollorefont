import { Component, OnInit } from '@angular/core';
import {BehaviorService} from '../../../services/common/behavior.service';

@Component({
  selector: 'app-user-run',
  templateUrl: './user-run.component.html',
  styleUrls: ['./user-run.component.css']
})
export class UserRunComponent implements OnInit {

  constructor(
    private behaviorService: BehaviorService,
  ) { }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Gestion Utilisateur', 'User']);
  }

}
