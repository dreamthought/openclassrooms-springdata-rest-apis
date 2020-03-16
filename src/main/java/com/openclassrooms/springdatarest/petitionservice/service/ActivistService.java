package com.openclassrooms.springdatarest.petitionservice.service;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.repository.ActivistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Provides capabilities for creating and managing Activists
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ActivistService {

    @Autowired
    ActivistRepository activistRepository;

    /**
     * Creates a new activist in our system
     * @param activist to be created
     * @return Activist created
     */
    public Activist createActivist(Activist activist) {
        return activistRepository.save(activist);
    }

    /**
     * Fetches an Activist from the database
     * @param id of the activist
     * @return Optional&gt;Activist&lt; retrieved
     * @throws EntityNotFoundException when the Activist is not there
     */
    public Optional<Activist> getActivistById(Long id) {
        return activistRepository.findById(id);
    }


    public ModificationType updateActivist(Activist activist) {
        // Check if the activist already exists
        Long activistId = activist.getId();
        Boolean activistExists = null != activistId && activistRepository.existsById(activistId);

        // Fetch the DB version
        // Create or update the activist
        if (! activistExists) {
            activistRepository.save(activist);
            return ModificationType.CREATED;
        }

        Activist storedActivist = mergeWithDbActivist(activist, activistId);
        activistRepository.save(storedActivist);

        return ModificationType.MODIFIED;
    }

    private Activist mergeWithDbActivist(Activist activist, Long activistId) {
        Activist storedActivist = getActivistById(activistId).get();
        storedActivist.setAddress(activist.getAddress());
        storedActivist.setName(activist.getName());
        storedActivist.setEmail(activist.getEmail());
        return storedActivist;
    }
}
