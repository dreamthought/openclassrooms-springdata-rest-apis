package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.service.ActivistService;
import com.openclassrooms.springdatarest.petitionservice.service.ModificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("/petitionservice/v1/activists")
public class ActivistController {

    @Autowired
    ActivistService activistService;

    @GetMapping("/{id}")
    public Activist getActivist(@PathVariable String id) {
        return activistService.getActivistById(Long.parseUnsignedLong(id))
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Activist Not Found"));
    }

    @PostMapping
    public Activist postActivist(@RequestBody Activist activist) {
        return activistService.createActivist(activist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putActivist(@PathVariable Long activistId, @RequestBody Activist activist) {
        if (activistId != activist.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new Exception("The Activist in the path does not agree with the Activist id"));
        }
        ModificationType modificationType = activistService.updateActivist(activist);

        // Fetch the current activist (fully populated) in the db
        Activist storedActivist = activistService.getActivistById(activistId).get();

        // Return HTTP 201 / Created
        if (ModificationType.CREATED.equals(modificationType)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(storedActivist);
        }

        // Return a 200 / OK
        return ResponseEntity.status(HttpStatus.OK).body(storedActivist);

    }

}
