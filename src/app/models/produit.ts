import {Gamme} from './gamme';
import {Marque} from './marque';
import {Modele} from './modele';
import {DemandeProduit} from './demande-produit';
import {EtatProduit} from './etat-produit';
import {MagasinProduit} from './magasin-produit';
import {Etat} from './etat';
import {Magasin} from './magasin';

export class Produit {

  id: number;
  numSerie: string;
  description: string;
  nbrStock: number;
  dateHeureStock: Date;
  gamme: Gamme;
  marque: Marque;
  modele: Modele;
  demandeProduits: DemandeProduit[];
  etatProduits: EtatProduit[];
  magazinProduits: MagasinProduit[];

  etat: Etat;
  magasin: Magasin;
  status: string;
  createdDate: string;
  lastModifiedDate: string;

}
