package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
/**
 * A Petition Domain Object
 */
public class Petition {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    public long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
