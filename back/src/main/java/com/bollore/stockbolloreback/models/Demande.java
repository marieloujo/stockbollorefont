package com.bollore.stockbolloreback.models;

import com.bollore.stockbolloreback.enumeration.Mouvement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demande extends AbstractAuditingEntity {

    private static final long serialVersionUID = -1695513586148413952L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "valider")
    private Boolean valider;

    @Column(name = "date_heure")
    private Instant dateHeure;

    @Enumerated(EnumType.STRING)
    @Column(name = "mouvement")
    private Mouvement mouvement;


    private String url;

    

   /* @ManyToOne
    @JsonIgnoreProperties(value = { "etatProduits", "demandes", "modele", "magazin", "marque", "gamme", "magazinProduits"    }, allowSetters = true)
    private Produit produit;*/

    @OneToMany(mappedBy = "demande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit", "personne" }, allowSetters = true)
    private Set<DemandeProduit> demandeProduits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "demandes", "profil", "service" }, allowSetters = true)
    private Personne personne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demande id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getValider() {
        return this.valider;
    }

    public Demande valider(Boolean valider) {
        this.valider = valider;
        return this;
    }

    public void setValider(Boolean valider) {
        this.valider = valider;
    }

    public Instant getDateHeure() {
        return this.dateHeure;
    }

    public Demande dateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
        return this;
    }

    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Mouvement getMouvement() {
        return this.mouvement;
    }

    public Demande mouvement(Mouvement mouvement) {
        this.mouvement = mouvement;
        return this;
    }

    public void setMouvement(Mouvement mouvement) {
        this.mouvement = mouvement;
    }

    /*public Produit getProduit() {
        return this.produit;
    }

    public Demande produit(Produit produit) {
        this.setProduit(produit);
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }*/

    public Set<DemandeProduit> getDemandeProduits() {
        return this.demandeProduits;
    }

    public Demande demandeProduit(Set<DemandeProduit> demandeProduits) {
        this.setDemandeProduits(demandeProduits);
        return this;
    }

    public Demande addDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.add(demandeProduits);
        demandeProduits.setDemande(this);
        return this;
    }

    public Demande removeDemandeProduit(DemandeProduit demandeProduits) {
        this.demandeProduits.remove(demandeProduits);
        demandeProduits.setProduit(null);
        return this;
    }

    public void setDemandeProduits(Set<DemandeProduit> demandeProduits) {
        if (this.demandeProduits != null) {
            this.demandeProduits.forEach(i -> i.setProduit(null));
        }
        if (demandeProduits != null) {
            demandeProduits.forEach(i -> i.setDemande(this));
        }
        this.demandeProduits = demandeProduits;
    }

    public Personne getPersonne() {
        return this.personne;
    }

    public Demande personne(Personne personne) {
        this.setPersonne(personne);
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demande)) {
            return false;
        }
        return id != null && id.equals(((Demande) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", valider=" + valider +
                ", dateHeure='" + dateHeure + '\'' +
                ", mouvement=" + mouvement +
                ", url='" + url + '\'' +
                ", demandeProduits=" + demandeProduits +
                ", personne=" + personne +
                '}';
    }


}
