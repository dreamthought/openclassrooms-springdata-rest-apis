package com.openclassrooms.springdatarest.petitionservice.repository;
import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "petitions", path = "petitions")
public interface PetitionRepository extends CrudRepository<Petition, Long> {
}
