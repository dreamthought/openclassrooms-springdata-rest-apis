package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.persistence.*;
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

    @OneToMany
    private Set<Signature> backerSignatures;

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
}
