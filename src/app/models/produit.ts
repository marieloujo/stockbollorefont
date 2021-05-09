import {Gamme} from './gamme';
import {Magasin} from './magasin';
import {Marque} from './marque';
import {Modele} from './modele';
import {DemandeProduit} from './demande-produit';
import {EtatProduit} from './etat-produit';

export class Produit {

  id: number;
  numSerie: number;
  description: string;
  nbrStock: number;
  dateHeureStock: Date;
  gamme: Gamme;
  marque: Marque;
  modele: Modele;
  demandeProduits: DemandeProduit[];
  etatProduits: EtatProduit[];

}
