package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ServiceB.
 */
@Entity
@Table(name = "service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceB extends AbstractAuditingEntity {

    private static final long serialVersionUID = -3168630783407211559L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "serviceB")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "demandes", "profil", "service" }, allowSetters = true)
    private Set<Personne> personnes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceB id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public ServiceB libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Personne> getPersonnes() {
        return this.personnes;
    }

    public ServiceB personnes(Set<Personne> personnes) {
        this.setPersonnes(personnes);
        return this;
    }

    public ServiceB addPersonne(Personne personne) {
        this.personnes.add(personne);
        personne.setServiceB(this);
        return this;
    }

    public ServiceB removePersonne(Personne personne) {
        this.personnes.remove(personne);
        personne.setServiceB(null);
        return this;
    }

    public void setPersonnes(Set<Personne> personnes) {
        if (this.personnes != null) {
            this.personnes.forEach(i -> i.setServiceB(null));
        }
        if (personnes != null) {
            personnes.forEach(i -> i.setServiceB(this));
        }
        this.personnes = personnes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceB)) {
            return false;
        }
        return id != null && id.equals(((ServiceB) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceB{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
