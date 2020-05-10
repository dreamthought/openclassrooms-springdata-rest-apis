package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.service.ActivistService;
import com.openclassrooms.springdatarest.petitionservice.service.ModificationType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Tag(name="activists", description="Operations on Activists; the users of the site")
@RequestMapping("/petitionservice/v1/activists")
public class ActivistController {

    @Autowired
    ActivistService activistService;

    private static final Integer PAGE_SIZE=10;

    @Operation(
            summary = "Gets all users",
            description = "Fetch Activists by a user provided page number. Defaults to page 0.",
            responses = {
                    @ApiResponse(description = "A page of Activists")
            }
    )
    @GetMapping
    public Page<Activist> getActivistCollection(
            @Parameter(example = "1", description = "Page number you would like to fetch.")
            @RequestParam(defaultValue = "0", value = "page", required = false) Integer page) {
        return activistService.getPagedActivists(page, PAGE_SIZE);
    }

    @Operation(
            summary = "Gets a specific activist",
            description = "Fetch a particular activist",
            responses = {
                    @ApiResponse(description = "A page of Activists"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The provided activist does not exist")
            }
    )
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
