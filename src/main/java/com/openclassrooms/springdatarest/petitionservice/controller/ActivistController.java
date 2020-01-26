package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.service.ActivistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivistController {

    @Autowired
    ActivistService activistService;

    @PostMapping("/petitionservice/v1/activists")
    public Activist postActivist(@RequestBody Activist activist) {
        return activistService.createActivist(activist);
    }
}
