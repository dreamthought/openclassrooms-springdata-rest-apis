package com.openclassrooms.springdatarest.petitionservice.repository;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource(collectionResourceRel = "patch-petitions", path = "patch-petitions")
public interface PetitionRepository extends CrudRepository<Petition, Long> {

    @RestResource(exported = false)
    List<Petition> findByTitleContains(@RequestParam String filter);

    @RestResource(exported = false)
    @Override
    Optional<Petition> findById(Long aLong);

    @RestResource(exported = false)
    @Override
    Iterable<Petition> findAll();

    @RestResource(exported = false)
    @Override
    void deleteById(Long aLong);

    @RestResource(exported = false)
    @Override
    void deleteAll();
}