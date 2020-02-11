package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import com.openclassrooms.springdatarest.petitionservice.service.ModificationType;
import com.openclassrooms.springdatarest.petitionservice.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.openclassrooms.springdatarest.petitionservice.service.ModificationType.CREATED;

@RestController
@RequestMapping("/petitionservice/v1/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;

    // HTTP Handlers
    @GetMapping()
    public List<Petition> listPetitions() {
        return petitionService.listAllPetitions();
    }

    @GetMapping("/{id}")
    public Petition getPetition(@PathVariable String id) {
        // TODO: If you were building this for Real you'd return a 404
        // Checkout OC's course on buildiing springboot microservices
        Optional<Petition> petition = petitionService.getPetition(Long.parseLong(id));

        // Return a NOT_FOUND / 404 error if we don't have a petition
        if (petition.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Petition Not Found");
        }
        return petition.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Petition postPetition(@RequestBody Petition petition) {
        return petitionService.createPetition(petition);
    }

    @PostMapping("/{id}/backer-signatures")
    @ResponseStatus(HttpStatus.CREATED)
    public Signature postBackerSignature(@PathVariable Long id, @RequestBody Signature signature) {
        try {
            return petitionService.backPetition(id, signature);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Petition Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePetition(@PathVariable Long id) {
        petitionService.deletePetition(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Petition> putPetition(@PathVariable Long id, @RequestBody Petition petition){
        // Indicate that this is an invalid request
        // The id in the path differs from the id in the Petition
        if (id != petition.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    header("X-ERROR-REASON", "Your id's don't agree!" ).
                    body(petition);
        }

        ModificationType modification = petitionService.modifyPetition(petition);

        // return 201 on create
        if (CREATED.equals(modification)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(petition);
        }

        // return 200 on update
        return ResponseEntity.status(HttpStatus.OK).body(petition);
    }

}
