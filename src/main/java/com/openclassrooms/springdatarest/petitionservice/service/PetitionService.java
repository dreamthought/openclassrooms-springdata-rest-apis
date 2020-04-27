package com.openclassrooms.springdatarest.petitionservice.service;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import com.openclassrooms.springdatarest.petitionservice.repository.PetitionRepository;
import com.openclassrooms.springdatarest.petitionservice.repository.SignatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetitionService {

    private static Logger LOGGER = LoggerFactory.getLogger(PetitionService.class.getName());

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private SignatureRepository signatureRepository;

    public List<Petition> searchPetitionTitles(String searchTerm) {
        return petitionRepository.findByTitleContains(searchTerm);
    }

    public Signature backPetition(Long petitionId, Signature signature) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(()->new EntityNotFoundException("Cound not find petition " + petitionId));

        // save signature
        signature.setPetition(petition);
        signature = signatureRepository.save(signature);

        // Add to petition
        petition.getBackerSignatures().add(signature);
        petitionRepository.save(petition);
        return signature;
    }

    public List<Petition> listAllPetitions() {
        return (List) petitionRepository.findAll();
    }

    public Optional<Petition> getPetition(Long id) {
        return petitionRepository.findById(id);
    }

    public Petition createPetition(Petition petition) {
        return petitionRepository.save(petition);
    }

    /**
     * Removes a petition from our repository
     * @param petitionId of the petition to be removed
     */
    public void deletePetition(long petitionId) {
        LOGGER.info("Deleting petition:", petitionId);
        petitionRepository.deleteById(petitionId);
        LOGGER.info("Removed petition:", petitionId);
    }

    /**
     * Modify a petition
     * @param petition entity
     * @return PetitionModification to indicate if we've created or updated
     */
    public ModificationType modifyPetition(Petition petition) {
        Long id = petition.getId();

        // We have a valid id and it belongs to an existing petition
        Boolean petitionExists = null != id && petitionRepository.existsById(id);

        // Merge the petitions
        if (petitionExists) {
            petition = mergeWithStoredPetition(id, petition);
        }

        // Create or update the petition
        petitionRepository.save(petition);

        // Indicate the Petition was created
        if (!petitionExists) {
            return ModificationType.CREATED;
        }

        // Indicate the petition was modified
        return ModificationType.MODIFIED;
    }

    private Petition mergeWithStoredPetition(Long id, Petition petition) {
        Petition currentPetition = getPetition(id).get();
        // Copy over ALL the fields but backerSignatures
        currentPetition.setTitle(petition.getTitle());
        currentPetition.setSponsor(petition.getSponsor());
        petition = currentPetition;
        return petition;
    }
}
