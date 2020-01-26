package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.service.ActivistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

}
