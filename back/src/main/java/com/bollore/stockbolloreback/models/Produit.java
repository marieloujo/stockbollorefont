package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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

    public Produit() {
    }

    public Produit(Long id, @NotNull String numSerie, String description, Instant dateHeureStock) {
        this.id = id;
        this.numSerie = numSerie;
        this.description = description;
        this.dateHeureStock = dateHeureStock;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit id(Long id) {
        this.id = id;
        return this;
    }

    public String getNumSerie() {
        return this.numSerie;
    }

    public Produit numSerie(String numSerie) {
        this.numSerie = numSerie;
        return this;
    }

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

    public String getDescription() {
        return this.description;
    }

    public Produit description(String description) {
        this.description = description;
        return this;
    }

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

    public Instant getDateHeureStock() {
        return this.dateHeureStock;
    }

    public Produit dateHeureStock(Instant dateHeureStock) {
        this.dateHeureStock = dateHeureStock;
        return this;
    }

    public void setDateHeureStock(Instant dateHeureStock) {
        this.dateHeureStock = dateHeureStock;
    }

    public Set<EtatProduit> getEtatProduits() {
        return this.etatProduits;
    }

    public Produit etatProduits(Set<EtatProduit> etatProduits) {
        this.setEtatProduits(etatProduits);
        return this;
    }

    public Produit addEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.add(etatProduit);
        etatProduit.setProduit(this);
        return this;
    }

    public Produit removeEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.remove(etatProduit);
        etatProduit.setProduit(null);
        return this;
    }

    public void setEtatProduits(Set<EtatProduit> etatProduits) {
        if (this.etatProduits != null) {
            this.etatProduits.forEach(i -> i.setProduit(null));
        }
        if (etatProduits != null) {
            etatProduits.forEach(i -> i.setProduit(this));
        }
        this.etatProduits = etatProduits;
    }

    public Set<DemandeProduit> getDemandeProduits() {
        return this.demandeProduits;
    }

    public Produit demandeProduit(Set<DemandeProduit> demandeProduits) {
        this.setDemandeProduits(demandeProduits);
        return this;
    }

    public Produit addDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.add(demandeProduits);
        demandeProduits.setProduit(this);
        return this;
    }

    public Produit removeDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.remove(demandeProduits);
        demandeProduits.setProduit(null);
        return this;
    }

    public void setDemandeProduits(Set<DemandeProduit> demandeProduits) {
        if (this.demandeProduits != null) {
            this.demandeProduits.forEach(i -> i.setProduit(null));
        }
        if (demandeProduits != null) {
            demandeProduits.forEach(i -> i.setProduit(this));
        }
        this.demandeProduits = demandeProduits;
    }

    public Set<MagazinProduit> getMagazinProduits() {
        return this.magazinProduits;
    }

    public Produit magazinProduits(Set<MagazinProduit> magazinProduits) {
        this.setMagazinProduits(magazinProduits);
        return this;
    }

    public Produit addMagazinProduits(MagazinProduit magazinProduits) {
        this.magazinProduits.add(magazinProduits);
        magazinProduits.setProduit(this);
        return this;
    }

    public Produit removeMagazinProduits(MagazinProduit magazinProduits) {
        this.magazinProduits.remove(magazinProduits);
        magazinProduits.setProduit(null);
        return this;
    }

    public void setMagazinProduits(Set<MagazinProduit> magazinProduits) {
        if (this.magazinProduits != null) {
            this.magazinProduits.forEach(i -> i.setProduit(null));
        }
        if (magazinProduits != null) {
            magazinProduits.forEach(i -> i.setProduit(this));
        }
        this.magazinProduits = magazinProduits;
    }

    public Modele getModele() {
        return this.modele;
    }

    public Produit modele(Modele modele) {
        this.setModele(modele);
        return this;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Marque getMarque() {
        return this.marque;
    }

    public Produit marque(Marque marque) {
        this.setMarque(marque);
        return this;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Gamme getGamme() {
        return gamme;
    }

    public Produit gamme(Gamme gamme) {
        this.setGamme(gamme);
        return this;
    }

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

}
