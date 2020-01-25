package com.openclassrooms.springdatarest.petitionservice.domain;

import javax.persistence.Embeddable;

/**
 * A value object for postal addresses
 */
@Embeddable
public class PostalAddress {
    private static final int MAX_ZIP_LENGTH = 10;
    private String streetAddress;

    private String zipCode;

    /**
     * Returns the street address portion
     * @return street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the street address portion
     * @param streetAddress portion
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Returns the zip of this postal address
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Will allow you to set a legal zip code or throw
     * @param zipCode of this address
     * @throws RuntimeException with details
     **/
    public void setZipCode(String zipCode) {
        // We might have custom business rules like setting a max length for a zip code
        if (null==zipCode || zipCode.length() > MAX_ZIP_LENGTH) {
            // FIXME: In the real world this would be a custom exception
            throw new RuntimeException("Bad ZipCode");
        }

        this.zipCode = zipCode;
    }
}
