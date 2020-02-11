package com.openclassrooms.springdatarest.petitionservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * An activist is the user of this system who backs and sponsors campaigns
 */
@Entity
public class Activist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private PostalAddress address;

    private String email;

    /**
     * Gets the id of the current Activist
     * @return id of this entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of this activist
     * @param id of activist
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the activist
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the activist
     * @param name of activist
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the activist
     * @return the address
     */
    public PostalAddress getAddress() {
        return address;
    }

    /**
     * Sets the address of the activist
     * @param address of activist
     */
    public void setAddress(PostalAddress address) {
        this.address = address;
    }


    /**
     * Gets the email of the activist
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the activist's email address
     * @param email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
