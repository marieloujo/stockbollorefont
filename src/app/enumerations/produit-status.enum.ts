export enum ProduitStatus {

    EN_STOCK = 'En stock',
    EN_UTILISAION = 'En utilisation',
    MISE_AU_REBUT = "Mise au rebut",
    EN_REPARATION = "En réparation",

    EN_ATTENTE_VALIDATION = "En attente de validation STANDARD",
    EN_ATTENTE_DE_MISE_AU_REBUT = "En attente de mise au REBUT",
    EN_ATTENTE_ENVOIE_REPARATION = "En attente d'envoie en REPARATION",

    EN_ATTENTE_LIVRAISON = "En attente de livraison",
    EN_ATTENTE_LIVRAISON_REP= "En attente de livraison pour REPARATION",
    EN_ATTENTE_LIVRAISON_REB= "En attente de livraison pour REBUT",

    EN_ATTENTE_SORTIE_MAG_REB= "En attente de sortie de Magasin pour REBUT",
    EN_ATTENTE_SORTIE_MAG_REP= "En attente de sortie de Magasin pour REPARATION",
    EN_ATTENTE_SORTIE_MAG_STA= "En attente de sortie de Magasin pour cas STANDARD",

    EN_ATTENTE_RECEPTION_STA= "En attente de Reception pour cas STANDARD",
    EN_ATTENTE_RECEPTION_REB= "En attente de Reception pour REBUT",
    EN_ATTENTE_RECEPTION_REP= "En attente de Reception pour REPARATION",
    
    
    EN_ATTENTE_DE_RETOUR = "En attente de RETOUR",
}

