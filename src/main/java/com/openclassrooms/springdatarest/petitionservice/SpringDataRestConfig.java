package com.openclassrooms.springdatarest.petitionservice;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // Configure Petition
        config.getExposureConfiguration()
                // Specify entity
                .forDomainType(Petition.class)
                // Provide a lambda to disable HTTP Methods for single entities
                .withItemExposure((metadata, itemHttpMethods) ->
                        itemHttpMethods.disable(
                                HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE))
                // Provide a lambda to disable HTTP Methods for collections
                .withCollectionExposure((collectionMetdata, collectionHttpMethods) ->
                        collectionHttpMethods.disable(
                                HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE));

        // Configure Signature
        config.getExposureConfiguration().forDomainType(Signature.class)
                .withItemExposure(((metdata, httpMethods) ->
                        httpMethods.disable(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH)))
                .withCollectionExposure((metdata, httpMethods) ->
                        httpMethods.disable(HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH));

    }
}
