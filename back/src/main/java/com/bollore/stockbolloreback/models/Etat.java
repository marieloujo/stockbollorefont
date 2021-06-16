package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A Etat.
 */
@Entity
@Table(name = "etat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Etat extends AbstractAuditingEntity {

    private static final long serialVersionUID = 2802260907636140822L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy = "etat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = {"produit", "etat"}, allowSetters = true)
    private Set<EtatProduit> etatProduits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Etat libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Etat code(String code) {
        this.code = code;
        return this;
    }

    public Set<EtatProduit> getEtatProduits() {
        return this.etatProduits;
    }

    public void setEtatProduits(Set<EtatProduit> etatProduits) {
        if (this.etatProduits != null) {
            this.etatProduits.forEach(i -> i.setEtat(null));
        }
        if (etatProduits != null) {
            etatProduits.forEach(i -> i.setEtat(this));
        }
        this.etatProduits = etatProduits;
    }

    public Etat etatProduits(Set<EtatProduit> etatProduits) {
        this.setEtatProduits(etatProduits);
        return this;
    }

    public Etat addEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.add(etatProduit);
        etatProduit.setEtat(this);
        return this;
    }

    public Etat removeEtatProduit(EtatProduit etatProduit) {
        this.etatProduits.remove(etatProduit);
        etatProduit.setEtat(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etat)) {
            return false;
        }
        return id != null && id.equals(((Etat) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Etat{" +
                "id=" + getId() +
                "code=" + getCode() +
                ", libelle='" + getLibelle() + "'" +
                "}";
    }
}
