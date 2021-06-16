package com.bollore.stockbolloreback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProfilAction.
 */
@Entity
@Table(name = "profil_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfilAction extends AbstractAuditingEntity {

    private static final long serialVersionUID = -6104530073074895260L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_heure")
    private Instant dateHeure;

    @Column(name = "statut")
    private Boolean statut;

    @ManyToOne
    @JsonIgnoreProperties(value = { "profilActions" }, allowSetters = true)
    private ActionB actionB;

    @ManyToOne
    @JsonIgnoreProperties(value = { "personnes", "profilActions" }, allowSetters = true)
    private Profil profil;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfilAction id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getDateHeure() {
        return this.dateHeure;
    }

    public ProfilAction dateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
        return this;
    }

    public void setDateHeure(Instant dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Boolean getStatut() {
        return this.statut;
    }

    public ProfilAction statut(Boolean statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Boolean statut) {
        this.statut = statut;
    }

    public ActionB getActionB() {
        return this.actionB;
    }

    public ProfilAction action(ActionB actionB) {
        this.setActionB(actionB);
        return this;
    }

    public void setActionB(ActionB actionB) {
        this.actionB = actionB;
    }

    public Profil getProfil() {
        return this.profil;
    }

    public ProfilAction profil(Profil profil) {
        this.setProfil(profil);
        return this;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfilAction)) {
            return false;
        }
        return id != null && id.equals(((ProfilAction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfilAction{" +
            "id=" + getId() +
            ", dateHeure='" + getDateHeure() + "'" +
            ", statut='" + getStatut() + "'" +
            "}";
    }
}
