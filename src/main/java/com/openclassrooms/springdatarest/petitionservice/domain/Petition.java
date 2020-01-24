package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * A Petition Domain Object
 */
@Entity
public class Petition {

    @Id
    @GeneratedValue
    private long id;

    private String title;

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
}
