package com.openclassrooms.springdatarest.petitionservice.domain;

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
    private String address;
    private String email;

    /**
     * Gets the id of the current Activist
     * @return id of this entity
     */
    public Long getId() {
        return id;
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
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the activist
     * @param address of activist
     */
    public void setAddress(String address) {
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
