package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ActionB.
 */
@Entity
@Table(name = "action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActionB extends AbstractAuditingEntity {

    private static final long serialVersionUID = 7692880515213190644L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "actionB")
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

    public ActionB id(Long id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public ActionB libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<ProfilAction> getProfilActions() {
        return this.profilActions;
    }

    public ActionB profilActions(Set<ProfilAction> profilActions) {
        this.setProfilActions(profilActions);
        return this;
    }

    public ActionB addProfilAction(ProfilAction profilAction) {
        this.profilActions.add(profilAction);
        profilAction.setActionB(this);
        return this;
    }

    public ActionB removeProfilAction(ProfilAction profilAction) {
        this.profilActions.remove(profilAction);
        profilAction.setActionB(null);
        return this;
    }

    public void setProfilActions(Set<ProfilAction> profilActions) {
        if (this.profilActions != null) {
            this.profilActions.forEach(i -> i.setActionB(null));
        }
        if (profilActions != null) {
            profilActions.forEach(i -> i.setActionB(this));
        }
        this.profilActions = profilActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionB)) {
            return false;
        }
        return id != null && id.equals(((ActionB) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActionB{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
