package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Modele.
 */
@Entity
@Table(name = "modele")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Modele extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1003535715553338042L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "modele")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "etatProduits", "demandeProduits", "modele", "marque", "gamme", "magazinProduits" }, allowSetters = true)
    private Set<Produit> produits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Modele id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Modele libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Produit> getProduits() {
        return this.produits;
    }

    public Modele produits(Set<Produit> produits) {
        this.setProduits(produits);
        return this;
    }

    public Modele addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setModele(this);
        return this;
    }

    public Modele removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setModele(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        if (this.produits != null) {
            this.produits.forEach(i -> i.setModele(null));
        }
        if (produits != null) {
            produits.forEach(i -> i.setModele(this));
        }
        this.produits = produits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modele)) {
            return false;
        }
        return id != null && id.equals(((Modele) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modele{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
