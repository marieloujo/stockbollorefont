import {Produit} from './produit';
import {Etat} from './etat';

export class EtatProduit {
  id: number;
  dateHeure: Date;
  actuel: boolean;
  produit: Produit;
  etat: Etat;
}
