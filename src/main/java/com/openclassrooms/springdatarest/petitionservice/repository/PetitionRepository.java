package com.openclassrooms.springdatarest.petitionservice.repository;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "patch-petitions", path = "patch-petitions")
public interface PetitionRepository extends CrudRepository<Petition, Long> {

    @RestResource(exported = true)
    @Override
    <S extends Petition> S save(S s);
}
