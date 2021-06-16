package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EtatProduit.
 */
@Entity
@Table(name = "etat_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtatProduit extends AbstractAuditingEntity {

    private static final long serialVersionUID = -194966322662815836L;

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
    @JsonIgnoreProperties(value = { "etatProduits" }, allowSetters = true)
    private Etat etat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EtatProduit id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getDateHeure() {
        return this.dateHeure;
    }

    public EtatProduit dateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
        return this;
    }

    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Boolean getActuel() {
        return this.actuel;
    }

    public EtatProduit actuel(Boolean actuel) {
        this.actuel = actuel;
        return this;
    }

    public void setActuel(Boolean actuel) {
        this.actuel = actuel;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public EtatProduit produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Etat getEtat() {
        return this.etat;
    }

    public EtatProduit etat(Etat etat) {
        this.setEtat(etat);
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatProduit)) {
            return false;
        }
        return id != null && id.equals(((EtatProduit) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatProduit{" +
            "id=" + getId() +
            ", dateHeure='" + getDateHeure() + "'" +
            ", actuel='" + getActuel() + "'" +
            "}";
    }
}
