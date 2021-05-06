import {Component, OnInit} from '@angular/core';
import {BehaviorService} from '../../services/common/behavior.service';

interface Historique {
  numserie: string;
  marque: string;
  modele: string;
  gamme: string;
  demandeur: string;
  date: string;
}

@Component({
  selector: 'app-historique-demande',
  templateUrl: './historique-demande.component.html',
  styleUrls: ['./historique-demande.component.css']
})
export class HistoriqueDemandeComponent implements OnInit {


  listOfData: Historique[] = [
    {
      numserie: '1002DFB852',
      marque: 'DELL',
      modele: 'A80',
      gamme: 'Ecran',
      demandeur: 'Pierre HOUNDO',
      date: '04 mai 2021',
    },
    {
      numserie: '875EFG6924',
      marque: 'HP',
      modele: 'D40',
      gamme: 'Souris',
      demandeur: 'Samson CATRAYE',
      date: '04 mai 2021',
    },
    {
      numserie: '658ADCF87G',
      marque: 'CASIO',
      modele: 'CP55',
      gamme: 'Projecteur',
      demandeur: 'Jacob VIGANVI',
      date: '05 mai 2021',
    },
    {
      numserie: '201ASC568R',
      marque: 'CASIO',
      modele: 'RA8',
      gamme: 'Rallonge',
      demandeur: 'Jacob VIGANVI',
      date: '05 mai 2021',
    },

  ];


  constructor(
    private behaviorService: BehaviorService,
  ) {
  }

  ngOnInit(): void {
    this.behaviorService.setBreadcrumbItems(['Accueil', 'Demande', 'Historique demande']);
  }

}
