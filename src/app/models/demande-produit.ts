import {Demande} from './demande';
import {Produit} from './produit';
import {Marque} from './marque';
import {Modele} from './modele';
import {Gamme} from './gamme';
import {Personne} from './personne';
//import {MagasinProduit} from './magasin-produit';
import {Etat} from './etat';

export class DemandeProduit {

  createdBy: string;
  createdDate: Date;
  lastModifiedBy: string;
  lastModifiedDate: Date;
  demande: Demande;
  description:	string;
  id: number;
  livrer: boolean;
  produit: Produit;
  valider: boolean;
  validateur: string;
  validationDate: Date;
  gestionnaire: string;
  
 // magasinProduit: MagasinProduit;
  marque: Marque;
  modele: Modele;
  gamme: Gamme;
  personne: Personne;
  etat: Etat;
  dateValidation: string;
  dateLivraison: string;
  dateRejet: string;

  // gestionnaire
  dateDemandeRetour: string;
  dateValidationRetour: string;
  dateRejetRetour: string;
  etatProduitRetour: string;

  // 1
  dateDemandeRetour1: string;
  dateValidationRetour1: string;
  dateRejetRetour1: string;
  etatProduitRetour1: string;

  // 2
  dateDemandeRetour2: string;
  dateValidationRetour2: string;
  dateRejetRetour2: string;
  etatProduitRetour2: string;

  // 3
  dateDemandeRetour3: string;
  dateValidationRetour3: string;
  dateRejetRetour3: string;
  etatProduitRetour3: string;

}
