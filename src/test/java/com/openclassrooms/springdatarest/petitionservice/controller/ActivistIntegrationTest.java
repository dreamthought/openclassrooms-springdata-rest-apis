package com.openclassrooms.springdatarest.petitionservice.controller;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.domain.PostalAddress;
import com.openclassrooms.springdatarest.petitionservice.service.ActivistService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.hamcrest.Matchers.is;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Given a running petition service with an Activist REST API")
public class ActivistIntegrationTest {

    public static final String ACTIVIST_NAME = "Act I. Vist";
    public static final String ACTIVIST_EMAIL = "richu@example.com";
    public static final String ACTIVIST_STREET = "10 This Place";
    public static final String ACTIVIST_ZIP = "90210";
    @MockBean
    private ActivistService activistService;

    @Autowired
    private PetitionController petitionController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When we receive a JSON POST with an Activist Then we should create it")
    public void givenAJsonPostRequest_whenItHasAValidActivist_thenWeShouldCreateAnActivist() throws Exception {

        // Just creates and activist and sets fields on it
        Activist expectedActivist = buildActivistFixture();
        JSONObject postedJson = new JSONObject();
        postedJson.put("name", ACTIVIST_NAME);
        postedJson.put("email", ACTIVIST_EMAIL);

        // Add address
        JSONObject address = new JSONObject();
        address.put("streetAddress", ACTIVIST_STREET);
        address.put("zipCode", ACTIVIST_ZIP);
        postedJson.put("address", address);

        // Setup mocks
        when(activistService.createActivist(any(Activist.class))).thenReturn(expectedActivist);

        // Act and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/petitionservice/v1/activists")
                .contentType(MediaType.APPLICATION_JSON).content(postedJson.toString()))
                .andExpect(jsonPath("$.name", is(ACTIVIST_NAME)));
    }

    private Activist buildActivistFixture() {
        Activist expectedActivist = new Activist();
        expectedActivist.setName(ACTIVIST_NAME);
        expectedActivist.setEmail(ACTIVIST_EMAIL);
        PostalAddress address = new PostalAddress();
        address.setStreetAddress(ACTIVIST_STREET);
        address.setZipCode(ACTIVIST_ZIP);
        return expectedActivist;
    }

}
