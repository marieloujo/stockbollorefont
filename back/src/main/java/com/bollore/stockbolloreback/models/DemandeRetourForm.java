package com.bollore.stockbolloreback.models;

import com.bollore.stockbolloreback.enumeration.EnumProduitEtat;

/**
 * The type Demande retour form.
 */
public class DemandeRetourForm {
    private Long demandeProduitId;
    private EnumProduitEtat etatProduitRetour;
    private Boolean canValidate;

    /**
     * Gets demande produit id.
     *
     * @return the demande produit id
     */
    public Long getDemandeProduitId() {
        return demandeProduitId;
    }

    /**
     * Sets demande produit id.
     *
     * @param demandeProduitId the demande produit id
     */
    public void setDemandeProduitId(Long demandeProduitId) {
        this.demandeProduitId = demandeProduitId;
    }

    /**
     * Gets etat produit retour.
     *
     * @return the etat produit retour
     */
    public EnumProduitEtat getEtatProduitRetour() {
        return etatProduitRetour;
    }

    /**
     * Sets etat produit retour.
     *
     * @param etatProduitRetour the etat produit retour
     */
    public void setEtatProduitRetour(EnumProduitEtat etatProduitRetour) {
        this.etatProduitRetour = etatProduitRetour;
    }

    /**
     * Gets can validate.
     *
     * @return the can validate
     */
    public Boolean getCanValidate() {
        return canValidate;
    }

    /**
     * Sets can validate.
     *
     * @param canValidate the can validate
     */
    public void setCanValidate(Boolean canValidate) {
        this.canValidate = canValidate;
    }
}
