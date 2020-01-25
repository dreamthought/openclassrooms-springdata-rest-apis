package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Represents a signature by an activist at a specific point in time
 */
@Entity
public class Signature {

    @Id
    @GeneratedValue
    private Long id;

    // An activist will sign many petitions
    @ManyToOne(optional = false)
    private Activist signedBy;

    // Each signature must belong to a specific petition
    @ManyToOne(optional=false)
    private Petition petition;

    private LocalDateTime signedAt;

    @ManyToOne
    private Activist sponsor;

    /**
     * Gets the Identity of this petition
     * @return id of the petition
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the activist who signed
     * @return the activist
     */
    public Activist getSignedBy() {
        return signedBy;
    }

    /**
     * Sets the activist who signed this petition
     * @param signedBy
     */
    public void setSignedBy(Activist signedBy) {
        this.signedBy = signedBy;
    }

    /**
     * Gets the activist who signed this petition
     * @return the activist
     */
    public LocalDateTime getSignedAt() {
        return signedAt;
    }

    /**
     * Sets the time at which the activist signed
     * @param signedAt
     */
    public void setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
    }

    /**
     * Returns the sponsor of this Petition
     * @return an Activist sponsor
     */
    public Activist getSponsor() {
        return sponsor;
    }

    /**
     * Sets the sponsor for this Petition
     * @param sponsor
     */
    public void setSponsor(Activist sponsor) {
        this.sponsor = sponsor;
    }
}
