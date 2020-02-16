package com.openclassrooms.springdatarest.petitionservice.repository;

import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "signatures", path = "signatures")
public interface SignatureRepository extends JpaRepository<Signature, Long> {

    @RestResource(exported = false)
    @Override
    <S extends Signature> S save(S s);

    @RestResource(exported = false)
    @Override
    void delete(Signature signature);

}
