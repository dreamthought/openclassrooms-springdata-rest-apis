package com.openclassrooms.springdatarest.petitionservice.repository;

        import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
        import org.springframework.data.repository.CrudRepository;

public interface PetitionRepository extends CrudRepository<Long, Petition> {
}
