package com.openclassrooms.springdatarest.petitionservice.service;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    public List<Petition> listAllPetitions() {
        return (List) petitionRepository.findAll();
    }
}
