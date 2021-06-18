package com.bollore.stockbolloreback.models;

import com.bollore.stockbolloreback.enumeration.ProduitStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The type Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Produit extends AbstractAuditingEntity {

    private static final long serialVersionUID = 8696800369151706033L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "num_serie", nullable = false, unique = true)
    private String numSerie;
    //private Long numSerie;

    /*@NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;*/

    @Column(name = "description")
    private String description;

    ///a retirer///
    /*@Column(name = "nbr_stock")
    private Integer nbrStock;*/

    @Column(name = "date_heure_stock")
    private Instant dateHeureStock;

    @OneToMany(mappedBy = "produit",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit", "etat" }, allowSetters = true)
    private Set<EtatProduit> etatProduits = new HashSet<>();

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit", "demande" }, allowSetters = true)
    private Set<DemandeProduit> demandeProduits = new HashSet<>();

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit", "magazin" }, allowSetters = true)
    private Set<MagazinProduit> magazinProduits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "produits" }, allowSetters = true)
    private Modele modele;

    @ManyToOne
    @JsonIgnoreProperties(value = { "produits" }, allowSetters = true)
    private Marque marque;

    @ManyToOne
    @JsonIgnoreProperties(value = { "produits" }, allowSetters = true)
    private Gamme gamme;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProduitStatus status;

    /**
     * Instantiates a new Produit.
     */
    public Produit() {
    }

    /**
     * Instantiates a new Produit.
     *
     * @param id             the id
     * @param numSerie       the num serie
     * @param description    the description
     * @param dateHeureStock the date heure stock
     */
    public Produit(Long id, @NotNull String numSerie, String description, Instant dateHeureStock) {
        this.id = id;
        this.numSerie = numSerie;
        this.description = description;
        this.dateHeureStock = dateHeureStock;
    }

    /**
     * Instantiates a new Produit.
     *
     * @param id              the id
     * @param numSerie        the num serie
     * @param description     the description
     * @param dateHeureStock  the date heure stock
     * @param etatProduits    the etat produits
     * @param demandeProduits the demande produits
     * @param modele          the modele
     * @param marque          the marque
     * @param magazinProduits the magazin produits
     */
    public Produit(Long id, @NotNull String numSerie, String description, Instant dateHeureStock, Set<EtatProduit> etatProduits, Set<DemandeProduit> demandeProduits, Modele modele, Marque marque, Set<MagazinProduit> magazinProduits) {
        this.id = id;
        this.numSerie = numSerie;
        this.description = description;
        this.dateHeureStock = dateHeureStock;
        this.etatProduits = etatProduits;
        this.demandeProduits = demandeProduits;
        this.modele = modele;
        this.marque = marque;
        this.magazinProduits = magazinProduits;
    }

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
     * Id produit.
     *
     * @param id the id
     * @return the produit
     */
    public Produit id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets num serie.
     *
     * @return the num serie
     */
    public String getNumSerie() {
        return this.numSerie;
    }

    /**
     * Num serie produit.
     *
     * @param numSerie the num serie
     * @return the produit
     */
    public Produit numSerie(String numSerie) {
        this.numSerie = numSerie;
        return this;
    }

    /**
     * Sets num serie.
     *
     * @param numSerie the num serie
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /*public String getLibelle() {
        return this.libelle;
    }

    public Produit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }*/

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Description produit.
     *
     * @param description the description
     * @return the produit
     */
    public Produit description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*public Integer getNbrStock() {
        return this.nbrStock;
    }

    public Produit nbrStock(Integer nbrStock) {
        this.nbrStock = nbrStock;
        return this;
    }

    public void setNbrStock(Integer nbrStock) {
        this.nbrStock = nbrStock;
    }*/

    /**
     * Gets date heure stock.
     *
     * @return the date heure stock
     */
    public Instant getDateHeureStock() {
        return this.dateHeureStock;
    }

    /**
     * Date heure stock produit.
     *
     * @param dateHeureStock the date heure stock
     * @return the produit
     */
    public Produit dateHeureStock(Instant dateHeureStock) {
        this.dateHeureStock = dateHeureStock;
        return this;
    }

    /**
     * Sets date heure stock.
     *
     * @param dateHeureStock the date heure stock
     */
    public void setDateHeureStock(Instant dateHeureStock) {
        this.dateHeureStock = dateHeureStock;
    }

    /**
     * Gets etat produits.
     *
     * @return the etat produits
     */
    public Set<EtatProduit> getEtatProduits() {
        return this.etatProduits;
    }

    /**
     * Etat produits produit.
     *
     * @param etatProduits the etat produits
     * @return the produit
     */
    public Produit etatProduits(Set<EtatProduit> etatProduits) {
        this.setEtatProduits(etatProduits);
        return this;
    }

    /**
     * Add etat produit produit.
     *
     * @param etatProduit the etat produit
     * @return the produit
     */
    public Produit addEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.add(etatProduit);
        etatProduit.setProduit(this);
        return this;
    }

    /**
     * Remove etat produit produit.
     *
     * @param etatProduit the etat produit
     * @return the produit
     */
    public Produit removeEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.remove(etatProduit);
        etatProduit.setProduit(null);
        return this;
    }

    /**
     * Sets etat produits.
     *
     * @param etatProduits the etat produits
     */
    public void setEtatProduits(Set<EtatProduit> etatProduits) {
        if (this.etatProduits != null) {
            this.etatProduits.forEach(i -> i.setProduit(null));
        }
        if (etatProduits != null) {
            etatProduits.forEach(i -> i.setProduit(this));
        }
        this.etatProduits = etatProduits;
    }

    /**
     * Gets demande produits.
     *
     * @return the demande produits
     */
    public Set<DemandeProduit> getDemandeProduits() {
        return this.demandeProduits;
    }

    /**
     * Demande produit produit.
     *
     * @param demandeProduits the demande produits
     * @return the produit
     */
    public Produit demandeProduit(Set<DemandeProduit> demandeProduits) {
        this.setDemandeProduits(demandeProduits);
        return this;
    }

    /**
     * Add demande produit produit.
     *
     * @param demandeProduits the demande produits
     * @return the produit
     */
    public Produit addDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.add(demandeProduits);
        demandeProduits.setProduit(this);
        return this;
    }

    /**
     * Remove demande produit produit.
     *
     * @param demandeProduits the demande produits
     * @return the produit
     */
    public Produit removeDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.remove(demandeProduits);
        demandeProduits.setProduit(null);
        return this;
    }

    /**
     * Sets demande produits.
     *
     * @param demandeProduits the demande produits
     */
    public void setDemandeProduits(Set<DemandeProduit> demandeProduits) {
        if (this.demandeProduits != null) {
            this.demandeProduits.forEach(i -> i.setProduit(null));
        }
        if (demandeProduits != null) {
            demandeProduits.forEach(i -> i.setProduit(this));
        }
        this.demandeProduits = demandeProduits;
    }

    /**
     * Gets magazin produits.
     *
     * @return the magazin produits
     */
    public Set<MagazinProduit> getMagazinProduits() {
        return this.magazinProduits;
    }

    /**
     * Magazin produits produit.
     *
     * @param magazinProduits the magazin produits
     * @return the produit
     */
    public Produit magazinProduits(Set<MagazinProduit> magazinProduits) {
        this.setMagazinProduits(magazinProduits);
        return this;
    }

    /**
     * Add magazin produits produit.
     *
     * @param magazinProduits the magazin produits
     * @return the produit
     */
    public Produit addMagazinProduits(MagazinProduit magazinProduits) {
        this.magazinProduits.add(magazinProduits);
        magazinProduits.setProduit(this);
        return this;
    }

    /**
     * Remove magazin produits produit.
     *
     * @param magazinProduits the magazin produits
     * @return the produit
     */
    public Produit removeMagazinProduits(MagazinProduit magazinProduits) {
        this.magazinProduits.remove(magazinProduits);
        magazinProduits.setProduit(null);
        return this;
    }

    /**
     * Sets magazin produits.
     *
     * @param magazinProduits the magazin produits
     */
    public void setMagazinProduits(Set<MagazinProduit> magazinProduits) {
        if (this.magazinProduits != null) {
            this.magazinProduits.forEach(i -> i.setProduit(null));
        }
        if (magazinProduits != null) {
            magazinProduits.forEach(i -> i.setProduit(this));
        }
        this.magazinProduits = magazinProduits;
    }

    /**
     * Gets modele.
     *
     * @return the modele
     */
    public Modele getModele() {
        return this.modele;
    }

    /**
     * Modele produit.
     *
     * @param modele the modele
     * @return the produit
     */
    public Produit modele(Modele modele) {
        this.setModele(modele);
        return this;
    }

    /**
     * Sets modele.
     *
     * @param modele the modele
     */
    public void setModele(Modele modele) {
        this.modele = modele;
    }

    /**
     * Gets marque.
     *
     * @return the marque
     */
    public Marque getMarque() {
        return this.marque;
    }

    /**
     * Marque produit.
     *
     * @param marque the marque
     * @return the produit
     */
    public Produit marque(Marque marque) {
        this.setMarque(marque);
        return this;
    }

    /**
     * Sets marque.
     *
     * @param marque the marque
     */
    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    /**
     * Gets gamme.
     *
     * @return the gamme
     */
    public Gamme getGamme() {
        return gamme;
    }

    /**
     * Gamme produit.
     *
     * @param gamme the gamme
     * @return the produit
     */
    public Produit gamme(Gamme gamme) {
        this.setGamme(gamme);
        return this;
    }

    /**
     * Sets gamme.
     *
     * @param gamme the gamme
     */
    public void setGamme(Gamme gamme) {
        this.gamme = gamme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + getId() +
                ", numSerie=" + getNumSerie() +
                ", description='" + getDescription() + "'" +
                ", dateHeureStock='" + getDateHeureStock() + "'" +
                "}";
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public ProduitStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(ProduitStatus status) {
        this.status = status;
    }
}
