import {Produit} from './produit';
import {Magasin} from './magasin';

export class MagasinProduit {

  id: number;
  dateHeure: Date;
  actuel: boolean;
  produit: Produit;
  magazin: Magasin;
  quantiteStock: number;

}
