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

    @Column(name = "date_mise_a_disposition")
    private Date dateMiseADisposition;

    @Column(name = "date_reception")
    private Date dateReception;


    // 1
    @Column(name = "date_demande_retour_1")
    private Date dateDemandeRetour1;

    @Column(name = "date_validation_retour_1")
    private Date dateValidationRetour1;

    @Column(name = "date_rejet_retour_1")
    private Date dateRejetRetour1;

    @Column(name = "etat_produit_retour_1")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EnumProduitEtat etatProduitRetour1;


    // 2
    @Column(name = "date_demande_retour_2")
    private Date dateDemandeRetour2;

    @Column(name = "date_validation_retour_2")
    private Date dateValidationRetour2;

    @Column(name = "date_rejet_retour_2")
    private Date dateRejetRetour2;

    @Column(name = "etat_produit_retour_2")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EnumProduitEtat etatProduitRetour2;

    // 3
    @Column(name = "date_demande_retour_3")
    private Date dateDemandeRetour3;

    @Column(name = "date_validation_retour_3")
    private Date dateValidationRetour3;

    @Column(name = "date_rejet_retour_3")
    private Date dateRejetRetour3;

    @Column(name = "etat_produit_retour_3")
    @Enumerated(value = EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EnumProduitEtat etatProduitRetour3;


    @ManyToOne
    @JsonIgnoreProperties(value = {"etatProduits", "demandeProduits", "modele", "marque", "gamme", "magazinProduits"}, allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = {"demandeProduits", "personne",}, allowSetters = true)
    private Demande demande;


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Id demande produit.
     *
     * @param id the id
     * @return the demande produit
     */
    public DemandeProduit id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets valider.
     *
     * @return the valider
     */
    public Boolean getValider() {
        return this.valider;
    }

    /**
     * Sets valider.
     *
     * @param valider the valider
     */
    public void setValider(Boolean valider) {
        this.valider = valider;
    }

    /**
     * Valider demande produit.
     *
     * @param valider the valider
     * @return the demande produit
     */
    public DemandeProduit valider(Boolean valider) {
        this.valider = valider;
        return this;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Description demande produit.
     *
     * @param description the description
     * @return the demande produit
     */
    public DemandeProduit description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Gets validateur.
     *
     * @return the validateur
     */
    public String getValidateur() {
        return this.validateur;
    }

    /**
     * Sets validateur.
     *
     * @param validateur the validateur
     */
    public void setValidateur(String validateur) {
        this.validateur = validateur;
    }

    /**
     * Validateur demande produit.
     *
     * @param validateur the validateur
     * @return the demande produit
     */
    public DemandeProduit validateur(String validateur) {
        this.validateur = validateur;
        return this;
    }

    /**
     * Gets gestionnaire.
     *
     * @return the gestionnaire
     */
    public String getGestionnaire() {
        return this.gestionnaire;
    }

    /**
     * Sets gestionnaire.
     *
     * @param gestionnaire the gestionnaire
     */
    public void setGestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * Gestionnaire demande produit.
     *
     * @param gestionnaire the gestionnaire
     * @return the demande produit
     */
    public DemandeProduit gestionnaire(String gestionnaire) {
        this.gestionnaire = gestionnaire;
        return this;
    }

    /**
     * Gets validation date.
     *
     * @return the validation date
     */
    public Instant getValidationDate() {
        return validationDate;
    }

    /**
     * Sets validation date.
     *
     * @param validationDate the validation date
     */
    public void setValidationDate(Instant validationDate) {
        this.validationDate = validationDate;
    }

    /**
     * Gets produit.
     *
     * @return the produit
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Sets produit.
     *
     * @param produit the produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * Produit demande produit.
     *
     * @param produit the produit
     * @return the demande produit
     */
    public DemandeProduit produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    /**
     * Gets livrer.
     *
     * @return the livrer
     */
    public Boolean getLivrer() {
        return this.livrer;
    }

    /**
     * Sets livrer.
     *
     * @param livrer the livrer
     */
    public void setLivrer(Boolean livrer) {
        this.livrer = livrer;
    }

    /**
     * Actuel demande produit.
     *
     * @param livrer the livrer
     * @return the demande produit
     */
    public DemandeProduit actuel(Boolean livrer) {
        this.livrer = livrer;
        return this;
    }

    /**
     * Gets demande.
     *
     * @return the demande
     */
    public Demande getDemande() {
        return this.demande;
    }

    /**
     * Sets demande.
     *
     * @param demande the demande
     */
    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    /**
     * Produit demande produit.
     *
     * @param demande the demande
     * @return the demande produit
     */
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

    /**
     * Gets status.
     *
     * @return the status
     */
    public EnumDemandeStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(EnumDemandeStatus status) {
        this.status = status;
    }

    /**
     * Gets date validation.
     *
     * @return the date validation
     */
    public Date getDateValidation() {
        return dateValidation;
    }

    /**
     * Sets date validation.
     *
     * @param dateValidation the date validation
     */
    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    /**
     * Sets date livraison.
     *
     * @param dateLivraison the date livraison
     */
    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    /**
     * Gets date rejet.
     *
     * @return the date rejet
     */
    public Date getDateRejet() {
        return dateRejet;
    }

    /**
     * Sets date rejet.
     *
     * @param dateRejet the date rejet
     */
    public void setDateRejet(Date dateRejet) {
        this.dateRejet = dateRejet;
    }

    /**
     * Gets date livraison.
     *
     * @return the date livraison
     */
    public Date getDateLivraison() {
        return dateLivraison;
    }

    /**
     * Gets date demande retour.
     *
     * @return the date demande retour
     */
    public Date getDateDemandeRetour() {
        return dateDemandeRetour;
    }

    /**
     * Sets date demande retour.
     *
     * @param dateDemandeRetour the date demande retour
     */
    public void setDateDemandeRetour(Date dateDemandeRetour) {
        this.dateDemandeRetour = dateDemandeRetour;
    }

    /**
     * Gets date validation retour.
     *
     * @return the date validation retour
     */
    public Date getDateValidationRetour() {
        return dateValidationRetour;
    }

    /**
     * Sets date validation retour.
     *
     * @param dateValidationRetour the date validation retour
     */
    public void setDateValidationRetour(Date dateValidationRetour) {
        this.dateValidationRetour = dateValidationRetour;
    }

    /**
     * Gets date rejet retour.
     *
     * @return the date rejet retour
     */
    public Date getDateRejetRetour() {
        return dateRejetRetour;
    }

    /**
     * Sets date rejet retour.
     *
     * @param dateRejetRetour the date rejet retour
     */
    public void setDateRejetRetour(Date dateRejetRetour) {
        this.dateRejetRetour = dateRejetRetour;
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
     * Gets date demande retour 1.
     *
     * @return the date demande retour 1
     */
    public Date getDateDemandeRetour1() {
        return dateDemandeRetour1;
    }

    /**
     * Sets date demande retour 1.
     *
     * @param dateDemandeRetour1 the date demande retour 1
     */
    public void setDateDemandeRetour1(Date dateDemandeRetour1) {
        this.dateDemandeRetour1 = dateDemandeRetour1;
    }

    /**
     * Gets date validation retour 1.
     *
     * @return the date validation retour 1
     */
    public Date getDateValidationRetour1() {
        return dateValidationRetour1;
    }

    /**
     * Sets date validation retour 1.
     *
     * @param dateValidationRetour1 the date validation retour 1
     */
    public void setDateValidationRetour1(Date dateValidationRetour1) {
        this.dateValidationRetour1 = dateValidationRetour1;
    }

    /**
     * Gets date rejet retour 1.
     *
     * @return the date rejet retour 1
     */
    public Date getDateRejetRetour1() {
        return dateRejetRetour1;
    }

    /**
     * Sets date rejet retour 1.
     *
     * @param dateRejetRetour1 the date rejet retour 1
     */
    public void setDateRejetRetour1(Date dateRejetRetour1) {
        this.dateRejetRetour1 = dateRejetRetour1;
    }

    /**
     * Gets etat produit retour 1.
     *
     * @return the etat produit retour 1
     */
    public EnumProduitEtat getEtatProduitRetour1() {
        return etatProduitRetour1;
    }

    /**
     * Sets etat produit retour 1.
     *
     * @param etatProduitRetour1 the etat produit retour 1
     */
    public void setEtatProduitRetour1(EnumProduitEtat etatProduitRetour1) {
        this.etatProduitRetour1 = etatProduitRetour1;
    }

    /**
     * Gets date demande retour 2.
     *
     * @return the date demande retour 2
     */
    public Date getDateDemandeRetour2() {
        return dateDemandeRetour2;
    }

    /**
     * Sets date demande retour 2.
     *
     * @param dateDemandeRetour2 the date demande retour 2
     */
    public void setDateDemandeRetour2(Date dateDemandeRetour2) {
        this.dateDemandeRetour2 = dateDemandeRetour2;
    }

    /**
     * Gets date validation retour 2.
     *
     * @return the date validation retour 2
     */
    public Date getDateValidationRetour2() {
        return dateValidationRetour2;
    }

    /**
     * Sets date validation retour 2.
     *
     * @param dateValidationRetour2 the date validation retour 2
     */
    public void setDateValidationRetour2(Date dateValidationRetour2) {
        this.dateValidationRetour2 = dateValidationRetour2;
    }

    /**
     * Gets date rejet retour 2.
     *
     * @return the date rejet retour 2
     */
    public Date getDateRejetRetour2() {
        return dateRejetRetour2;
    }

    /**
     * Sets date rejet retour 2.
     *
     * @param dateRejetRetour2 the date rejet retour 2
     */
    public void setDateRejetRetour2(Date dateRejetRetour2) {
        this.dateRejetRetour2 = dateRejetRetour2;
    }

    /**
     * Gets etat produit retour 2.
     *
     * @return the etat produit retour 2
     */
    public EnumProduitEtat getEtatProduitRetour2() {
        return etatProduitRetour2;
    }

    /**
     * Sets etat produit retour 2.
     *
     * @param etatProduitRetour2 the etat produit retour 2
     */
    public void setEtatProduitRetour2(EnumProduitEtat etatProduitRetour2) {
        this.etatProduitRetour2 = etatProduitRetour2;
    }

    /**
     * Gets date demande retour 3.
     *
     * @return the date demande retour 3
     */
    public Date getDateDemandeRetour3() {
        return dateDemandeRetour3;
    }

    /**
     * Sets date demande retour 3.
     *
     * @param dateDemandeRetour3 the date demande retour 3
     */
    public void setDateDemandeRetour3(Date dateDemandeRetour3) {
        this.dateDemandeRetour3 = dateDemandeRetour3;
    }

    /**
     * Gets date validation retour 3.
     *
     * @return the date validation retour 3
     */
    public Date getDateValidationRetour3() {
        return dateValidationRetour3;
    }

    /**
     * Sets date validation retour 3.
     *
     * @param dateValidationRetour3 the date validation retour 3
     */
    public void setDateValidationRetour3(Date dateValidationRetour3) {
        this.dateValidationRetour3 = dateValidationRetour3;
    }

    /**
     * Gets date rejet retour 3.
     *
     * @return the date rejet retour 3
     */
    public Date getDateRejetRetour3() {
        return dateRejetRetour3;
    }

    /**
     * Sets date rejet retour 3.
     *
     * @param dateRejetRetour3 the date rejet retour 3
     */
    public void setDateRejetRetour3(Date dateRejetRetour3) {
        this.dateRejetRetour3 = dateRejetRetour3;
    }

    /**
     * Gets etat produit retour 3.
     *
     * @return the etat produit retour 3
     */
    public EnumProduitEtat getEtatProduitRetour3() {
        return etatProduitRetour3;
    }

    /**
     * Sets etat produit retour 3.
     *
     * @param etatProduitRetour3 the etat produit retour 3
     */
    public void setEtatProduitRetour3(EnumProduitEtat etatProduitRetour3) {
        this.etatProduitRetour3 = etatProduitRetour3;
    }

    /**
     * Gets date mise a disposition.
     *
     * @return the date mise a disposition
     */
    public Date getDateMiseADisposition() {
        return dateMiseADisposition;
    }

    /**
     * Sets date mise a disposition.
     *
     * @param dateMiseADisposition the date mise a disposition
     */
    public void setDateMiseADisposition(Date dateMiseADisposition) {
        this.dateMiseADisposition = dateMiseADisposition;
    }

    /**
     * Gets date reception.
     *
     * @return the date reception
     */
    public Date getDateReception() {
        return dateReception;
    }

    /**
     * Sets date reception.
     *
     * @param dateReception the date reception
     */
    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }
}
