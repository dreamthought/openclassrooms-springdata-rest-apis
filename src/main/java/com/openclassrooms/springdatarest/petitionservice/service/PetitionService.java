package com.openclassrooms.springdatarest.petitionservice.service;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import com.openclassrooms.springdatarest.petitionservice.repository.PetitionRepository;
import com.openclassrooms.springdatarest.petitionservice.repository.SignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private SignatureRepository signatureRepository;

    public Signature backPetition(Long petitionId, Signature signature) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(()->new EntityNotFoundException("Cound not find petition " + petitionId));
        // save signature
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
}
