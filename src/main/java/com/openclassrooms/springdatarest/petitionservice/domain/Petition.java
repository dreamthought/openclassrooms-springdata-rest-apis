package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
/**
 * A Petition Domain Object
 */
public class Petition {

    @Id
    private long id;

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
