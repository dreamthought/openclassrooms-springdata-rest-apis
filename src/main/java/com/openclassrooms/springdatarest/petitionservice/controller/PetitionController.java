package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/petitionservice/v1/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;

    @GetMapping()
    public List<Petition> listPetitions() {
        return petitionService.listAllPetitions();
    }

    @GetMapping("/{id}")
    public Petition getPetition(@PathVariable String id) {
        // TODO: If you were building this for Real you'd return a 404
        // Checkout OC's course on buildiing springboot microservices
        return petitionService.fetchPetition(Long.parseLong(id)).get();
    }

}
