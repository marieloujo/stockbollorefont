export class DemandeRetourForm {
    demandeProduitId: number;
    etatProduitRetour: string;
    canValidate: boolean;

    constructor() {
        this.canValidate = false;
    }
}
