import {Gamme} from './gamme';
import {Marque} from './marque';
import {Modele} from './modele';
import {DemandeProduit} from './demande-produit';
import {EtatProduit} from './etat-produit';
import {MagasinProduit} from './magasin-produit';

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

}
