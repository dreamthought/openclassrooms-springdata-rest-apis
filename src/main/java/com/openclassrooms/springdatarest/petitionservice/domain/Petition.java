package com.openclassrooms.springdatarest.petitionservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * A Petition Domain Object
 */
@Entity
public class Petition {

    @Id
    @GeneratedValue
    private long id;
    private String title;

    @ManyToOne(optional = false)
    private Activist sponsor;

    @OneToMany(orphanRemoval = true)
    private Set<Signature> backerSignatures = new HashSet<>();

    /**
     * Returns the id of this petition
     * @return id of petition
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the title of the petition
     * @param title of petition
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the title of this petition
     * @return title of petition
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns a Set of the Signatures for this petition
     * @return signatures for this petition
     */
    public Set<Signature> getBackerSignatures() {
        return backerSignatures;
    }

    /**
     * Returns the petition's sponsor
     * @return Activist sponsoring this petition
     */
    public Activist getSponsor() {
        return sponsor;
    }

    /**
     * Sets the sponsor of this petition
     * @param sponsor Activist
     */
    public void setSponsor(Activist sponsor) {
        this.sponsor = sponsor;
    }


}
