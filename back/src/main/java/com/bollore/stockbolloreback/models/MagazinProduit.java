package com.bollore.stockbolloreback.models;


import javax.persistence.*;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EtatProduit.
 */
@Entity
@Table(name = "magazin_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MagazinProduit extends AbstractAuditingEntity {

    private static final long serialVersionUID = -1716388116573095423L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_heure")
    private Instant dateHeure;

    @Column(name = "actuel", nullable = false)
    private Boolean actuel;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etatProduits", "demandeProduits", "modele", "marque", "gamme", "magazinProduits" }, allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = { "magazinProduits" }, allowSetters = true)
    private Magazin magazin;

    @Column(name = "quantite_stock")
    private Double quantiteStock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MagazinProduit id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getDateHeure() {
        return dateHeure;
    }

    public MagazinProduit dateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
        return this;
    }

    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Boolean getActuel() {
        return actuel;
    }

    public MagazinProduit actuel(Boolean actuel) {
        this.actuel = actuel;
        return this;
    }

    public void setActuel(Boolean actuel) {
        this.actuel = actuel;
    }

    public Produit getProduit() {
        return produit;
    }

    public MagazinProduit produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Magazin getMagazin() {
        return magazin;
    }

    public MagazinProduit magazin(Magazin magazin) {
        this.setMagazin(magazin);
        return this;
    }

    public void setMagazin(Magazin magazin) {
        this.magazin = magazin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MagazinProduit)) {
            return false;
        }
        return id != null && id.equals(((MagazinProduit) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MagazinProduit{" +
                "id=" + id +
                ", dateHeure=" + dateHeure +
                ", actuel=" + actuel +
                '}';
    }

    public Double getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Double quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}
