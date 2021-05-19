import {Demande} from './demande';
import {Produit} from './produit';

export class DemandeProduit {

  demande: Demande;
  description:	string;
  id: number;
  livrer: boolean;
  produit: Produit;
  valider: boolean;


}
