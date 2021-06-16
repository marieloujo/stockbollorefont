package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Profil.
 */
@Entity
@Table(name = "profil")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Profil extends AbstractAuditingEntity{

    private static final long serialVersionUID = -73231392511025040L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "profil")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "demandes", "profil", "service" }, allowSetters = true)
    private Set<Personne> personnes = new HashSet<>();

    @OneToMany(mappedBy = "profil")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "action", "profil" }, allowSetters = true)
    private Set<ProfilAction> profilActions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profil id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Profil libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Personne> getPersonnes() {
        return this.personnes;
    }

    public Profil personnes(Set<Personne> personnes) {
        this.setPersonnes(personnes);
        return this;
    }

    public Profil addPersonne(Personne personne) {
        this.personnes.add(personne);
        personne.setProfil(this);
        return this;
    }

    public Profil removePersonne(Personne personne) {
        this.personnes.remove(personne);
        personne.setProfil(null);
        return this;
    }

    public void setPersonnes(Set<Personne> personnes) {
        if (this.personnes != null) {
            this.personnes.forEach(i -> i.setProfil(null));
        }
        if (personnes != null) {
            personnes.forEach(i -> i.setProfil(this));
        }
        this.personnes = personnes;
    }

    public Set<ProfilAction> getProfilActions() {
        return this.profilActions;
    }

    public Profil profilActions(Set<ProfilAction> profilActions) {
        this.setProfilActions(profilActions);
        return this;
    }

    public Profil addProfilAction(ProfilAction profilAction) {
        this.profilActions.add(profilAction);
        profilAction.setProfil(this);
        return this;
    }

    public Profil removeProfilAction(ProfilAction profilAction) {
        this.profilActions.remove(profilAction);
        profilAction.setProfil(null);
        return this;
    }

    public void setProfilActions(Set<ProfilAction> profilActions) {
        if (this.profilActions != null) {
            this.profilActions.forEach(i -> i.setProfil(null));
        }
        if (profilActions != null) {
            profilActions.forEach(i -> i.setProfil(this));
        }
        this.profilActions = profilActions;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profil)) {
            return false;
        }
        return id != null && id.equals(((Profil) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profil{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
