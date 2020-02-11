package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.domain.Petition;
import com.openclassrooms.springdatarest.petitionservice.domain.Signature;
import com.openclassrooms.springdatarest.petitionservice.repository.ActivistRepository;
import com.openclassrooms.springdatarest.petitionservice.repository.PetitionRepository;
import com.openclassrooms.springdatarest.petitionservice.repository.SignatureRepository;
import com.openclassrooms.springdatarest.petitionservice.service.PetitionService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given a running petition service with kitten and puppy petitions")
class PetitionIntegrationTest {

    @Autowired
    PetitionRepository petitionRepository;

    @Autowired
    SignatureRepository signatureRepository;

    @Autowired
    ActivistRepository activistRepository;

    @Autowired
    PetitionService petitionService;
    @Autowired
    PetitionController petitionController;



    @Autowired
    MockMvc mvc;
    private long puppyPetitionId;
    Petition puppyPetition;
    private Activist sponsor;

    @BeforeEach
    public void setUpFixtures() {
        // Setup Activist
        sponsor = new Activist();
        sponsor.setName("Spencer d'Sponsor");
        sponsor = activistRepository.save(sponsor);

        // Setup Kitten Petition
        Petition kittenPetition = new Petition();
        kittenPetition.setTitle("Save the Kitten");
        kittenPetition.setSponsor(sponsor);
        petitionRepository.save(kittenPetition);

        // Setup Puppy Petition
        puppyPetition = new Petition();
        puppyPetition.setTitle("Save the Puppy");
        puppyPetition.setSponsor(sponsor);
        petitionRepository.save(puppyPetition);
        puppyPetitionId = puppyPetition.getId();
    }

    @Test
    @DisplayName("When a REST API client sends a GET /petitions Then we should see puppies and kittens")
    public void givenTwoPetitions_whenWeGetTheCollection_thenWeShouldSeeACollection() throws Exception {
        mvc.perform(get("/petitionservice/v1/petitions")).andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("*", hasSize(2))).
                andExpect(jsonPath("[0].title", is("Save the Kitten"))).
                andExpect(jsonPath("[1].title", is("Save the Puppy")));
    }

    @Test
    @DisplayName("When a REST API client sends a GET /petitions/2 then we should see puppies")
    public void givenTwoPetitions_whenWeGetTheEntity_thenWeShouldSeeAPetition() throws Exception {
        mvc.perform(get("/petitionservice/v1/petitions/" + puppyPetitionId )).andExpect(status().is2xxSuccessful()).
                andExpect(jsonPath("$.title", is("Save the Puppy")));
    }


    @Test
    @DisplayName("And the petition is signed When a REST API client sends a DELETE /petitions/2 then we should delete it")
    public void givenASignedPetitions_whenWeDeleteTheEntity_thenWeShouldRemoveIt() throws Exception {
        // Arrange : Sign the Petition
        Signature signature = new Signature();
        signature.setSignedBy(sponsor);
        signature.setPetition(petitionRepository.findById(puppyPetitionId).get());
        signatureRepository.save(signature);
        // Add the signature to the petition
        puppyPetition.getBackerSignatures().add(signature);
        petitionRepository.save(puppyPetition);

        // ACT : Delete the petition with the REST API
        mvc.perform(delete("/petitionservice/v1/petitions/" + puppyPetitionId )).
                andExpect(status().is2xxSuccessful());

        // ASSERT : Check the database
        assertThat( petitionRepository.findById(puppyPetitionId).isEmpty(), is(true) );
        assertThat( signatureRepository.findById(signature.getId()).isEmpty(), is(true));
    }

    @DisplayName("When modifying the title with PUT then the petition should represent this")
    public void givenAPuppyPetition_whenModifyingTheTitleWithPut_thenTheTitleShouldBeCorrect() throws Exception {
        // Arrange in @BeforeEach
        JSONObject putJson = new JSONObject();
        String mutatedTitle = "Mutated title";
        putJson.put("title", mutatedTitle);
        putJson.put("id", puppyPetitionId);

        // Act & Assert
        mvc.perform(MockMvcRequestBuilders.put("/petitionservice/v1/petitions")
                .contentType(MediaType.APPLICATION_JSON).content(putJson.toString())).
                andExpect( status().is2xxSuccessful() ).
                andExpect( jsonPath("$.title", is(mutatedTitle)));

    }

}
