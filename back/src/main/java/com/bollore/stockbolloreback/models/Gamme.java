package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Gamme.
 */

@Entity
@Table(name = "gamme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gamme extends AbstractAuditingEntity {

    private static final long serialVersionUID = 573067125628299014L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "nbr_stock")
    private Integer nbrStock;

    @Column(name = "stock_min")
    private Integer stockMin;

    @OneToMany(mappedBy = "modele")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etatProduits", "demandeProduits", "modele", "marque", "gamme" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    public Gamme() {
    }

    public Gamme(@NotNull String libelle, String description, Integer nbrStock, Integer stockMin) {
        this.libelle = libelle;
        this.description = description;
        this.nbrStock = nbrStock;
        this.stockMin = stockMin;
    }

    public Gamme(Long id, @NotNull String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gamme id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Gamme libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gamme description(String description) {
        this.description = description;
        return this;
    }

    public Integer getNbrStock() {
        return this.nbrStock;
    }

    public Gamme nbrStock(Integer nbrStock) {
        this.nbrStock = nbrStock;
        return this;
    }

    public void setNbrStock(Integer nbrStock) {
        this.nbrStock = nbrStock;
    }

    public Integer getStockMin() {
        return this.stockMin;
    }

    public Gamme stockMin(Integer stockMin) {
        this.stockMin = stockMin;
        return this;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.setGamme(null));
        }
        if (produits != null) {
            produits.forEach(i -> i.setGamme(this));
        }
        this.produits = produits;
    }

    public Gamme produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public Gamme addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setGamme(this);
        return this;
    }

    public Gamme removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setGamme(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gamme)) return false;

        return id != null && id.equals(((Gamme) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Gamme{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", nbrStock=" + getNbrStock()  + '\'' +
                ", stockMin=" + getStockMin()  + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
