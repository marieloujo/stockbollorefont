package com.bollore.stockbolloreback.models;


import com.bollore.stockbolloreback.enumeration.EnumDemandeStatus;
import com.bollore.stockbolloreback.enumeration.EnumProduitEtat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * A DemandeProduit.
 */
@Entity
@Table(name = "demande_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DemandeProduit extends AbstractAuditingEntity {

    private static final long serialVersionUID = -8125855668787102031L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "valider")
    private Boolean valider;

    @Column(name = "description")
    private String description;

    @Column(name = "livrer")
    private Boolean livrer;

    @Column(name = "validateur")
    private String validateur;

    @Column(name = "validationDate")
    private Instant validationDate;

    @Column(name = "gestionnaire")
    private String gestionnaire;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EnumDemandeStatus status;


    @Column(name = "date_validation")
    private Date dateValidation;

    @Column(name = "date_livraison")
    private Date dateLivraison;

    @Column(name = "date_rejet")
    private Date dateRejet;

    @Column(name = "date_demande_retour")
    private Date dateDemandeRetour;

    @Column(name = "date_validation_retour")
    private Date dateValidationRetour;

    @Column(name = "date_rejet_retour")
    private Date dateRejetRetour;

    @Column(name = "etat_produit_retour")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EnumProduitEtat etatProduitRetour;


    @ManyToOne
    @JsonIgnoreProperties(value = {"etatProduits", "demandeProduits", "modele", "marque", "gamme", "magazinProduits"}, allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = {"demandeProduits", "personne",}, allowSetters = true)
    private Demande demande;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandeProduit id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getValider() {
        return this.valider;
    }

    public void setValider(Boolean valider) {
        this.valider = valider;
    }

    public DemandeProduit valider(Boolean valider) {
        this.valider = valider;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DemandeProduit description(String description) {
        this.description = description;
        return this;
    }

    public String getValidateur() {
        return this.validateur;
    }

    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    public DemandeProduit validateur(String validateur) {
        this.validateur = validateur;
        return this;
    }

    public String getGestionnaire() {
        return this.gestionnaire;
    }

    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public DemandeProduit gestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
        return this;
    }

    public Instant getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Instant validationDate) {
        this.validationDate = validationDate;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public DemandeProduit produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    public Boolean getLivrer() {
        return this.livrer;
    }

    public void setLivrer(Boolean livrer) {
        this.livrer = livrer;
    }

    public DemandeProduit actuel(Boolean livrer) {
        this.livrer = livrer;
        return this;
    }

    public Demande getDemande() {
        return this.demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public DemandeProduit produit(Demande demande) {
        this.setDemande(demande);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeProduit)) {
            return false;
        }
        return id != null && id.equals(((DemandeProduit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "DemandeProduit{" +
                "id=" + id +
                ", valider=" + valider +
                ", description='" + description + '\'' +
                ", livrer=" + livrer +
                ", validateur='" + validateur + '\'' +
                ", validationDate=" + validationDate +
                ", gestionnaire='" + gestionnaire + '\'' +
                ", dateLivraison=" + dateLivraison +
                ", produit=" + produit +
                ", demande=" + demande +
                '}';
    }

    public EnumDemandeStatus getStatus() {
        return status;
    }

    public void setStatus(EnumDemandeStatus status) {
        this.status = status;
    }

    public Date getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public Date getDateRejet() {
        return dateRejet;
    }

    public void setDateRejet(Date dateRejet) {
        this.dateRejet = dateRejet;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public Date getDateDemandeRetour() {
        return dateDemandeRetour;
    }

    public void setDateDemandeRetour(Date dateDemandeRetour) {
        this.dateDemandeRetour = dateDemandeRetour;
    }

    public Date getDateValidationRetour() {
        return dateValidationRetour;
    }

    public void setDateValidationRetour(Date dateValidationRetour) {
        this.dateValidationRetour = dateValidationRetour;
    }

    public Date getDateRejetRetour() {
        return dateRejetRetour;
    }

    public void setDateRejetRetour(Date dateRejetRetour) {
        this.dateRejetRetour = dateRejetRetour;
    }

    public EnumProduitEtat getEtatProduitRetour() {
        return etatProduitRetour;
    }

    public void setEtatProduitRetour(EnumProduitEtat etatProduitRetour) {
        this.etatProduitRetour = etatProduitRetour;
    }
}
