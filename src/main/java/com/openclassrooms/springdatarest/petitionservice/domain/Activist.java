package com.openclassrooms.springdatarest.petitionservice.domain;
import javax.persistence.*;

/**
 * An activist is the user of this system who backs and sponsors campaigns
 */
@Entity
public class Activist {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;

    @Column(name="MIDDLENAME")
    private String middlename;

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
     * Can be used to set the id
     * @param id of this entity
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
     * Gets the surname of the activist
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Sets the surname of the activist
     * @param surname of activist
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

    /**
     * Get the middle name of the activist
     * @return the middle name
     */
    public String getMiddlename() {
        return middlename;
    }
    /**
     * Sets the middlename of the activist
     * @param middlename of activist
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }


}
