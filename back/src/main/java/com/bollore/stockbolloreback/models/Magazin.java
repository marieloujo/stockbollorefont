package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * A Magazin.
 */
@Entity
@Table(name = "magazin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Magazin extends AbstractAuditingEntity {

    private static final long serialVersionUID = 4219847563516825258L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "magazin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit", "magazin" }, allowSetters = true)
    private Set<MagazinProduit> magazinProduits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Magazin id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Magazin libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public Set<MagazinProduit> getMagazinProduits() {
        return this.magazinProduits;
    }

    public Magazin magazinProduit(Set<MagazinProduit> magazinProduits) {
        this.setMagazinProduits(magazinProduits);
        return this;
    }

    public Magazin addMagazinProduit(MagazinProduit magazinProduit) {
        this.magazinProduits.add(magazinProduit);
        magazinProduit.setMagazin(this);
        return this;
    }

    public Magazin removeMagazinProduit(MagazinProduit magazinProduit) {
        this.magazinProduits.remove(magazinProduit);
        magazinProduit.setMagazin(null);
        return this;
    }

    public void setMagazinProduits(Set<MagazinProduit> magazinProduits) {
        if (this.magazinProduits != null) {
            this.magazinProduits.forEach(i -> i.setMagazin(null));
        }
        if (magazinProduits != null) {
            magazinProduits.forEach(i -> i.setMagazin(this));
        }
        this.magazinProduits = magazinProduits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Magazin)) {
            return false;
        }
        return id != null && id.equals(((Magazin) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Magazin{" +
                "id=" + getId() +
                ", libelle='" + getLibelle() + "'" +
                "}";
    }
}
