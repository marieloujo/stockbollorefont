package com.bollore.stockbolloreback.models;

import com.bollore.stockbolloreback.enumeration.EnumProduitStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;


/**
 * The type Status produit.
 */
@Entity
@Table(name = "status_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StatusProduit extends AbstractAuditingEntity {

    private static final long serialVersionUID = -194966322662815836L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_heure")
    private Instant dateHeure;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etatProduits", "demandeProduits", "modele", "marque", "gamme", "magazinProduits" }, allowSetters = true)
    private Produit produit;

    @Column(name = "status")
    private EnumProduitStatus status;

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
     * Id status produit.
     *
     * @param id the id
     * @return the status produit
     */
    public StatusProduit id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Gets date heure.
     *
     * @return the date heure
     */
    public Instant getDateHeure() {
        return this.dateHeure;
    }

    /**
     * Date heure status produit.
     *
     * @param dateHeure the date heure
     * @return the status produit
     */
    public StatusProduit dateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
        return this;
    }

    /**
     * Sets date heure.
     *
     * @param dateHeure the date heure
     */
    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
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
     * Produit status produit.
     *
     * @param produit the produit
     * @return the status produit
     */
    public StatusProduit produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    /**
     * Sets produit.
     *
     * @param produit the produit
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatusProduit)) {
            return false;
        }
        return id != null && id.equals(((StatusProduit) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public EnumProduitStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(EnumProduitStatus status) {
        this.status = status;
    }
}
