import {Produit} from './produit';
import {EtatProduit} from './etat-produit';

export class Etat {

  id: number;
  code: string;
  libelle: string;
  etatProduits: EtatProduit[];

}
