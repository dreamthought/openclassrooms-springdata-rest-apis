package com.openclassrooms.springdatarest.petitionservice.service;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import com.openclassrooms.springdatarest.petitionservice.domain.PostalAddress;
import com.openclassrooms.springdatarest.petitionservice.repository.ActivistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Given an ActivistRepository")
class ActivistServiceTest {
    private static long TEST_ACTIVIST_ID = 100;

    @Mock
    ActivistRepository activistRepository;

    @InjectMocks
    ActivistService activistServiceUnderTest;

    private Activist activistFixture;

    @BeforeEach
    public void setupActivistFixture() {
        activistFixture = createActivistFixture(TEST_ACTIVIST_ID);
    }

    @Test
    @DisplayName("When modifying an existing Activist Then its state should change")
    public void givenAnExistingActivist_whenModified_thenItsStateShouldHaveChanged() {
        // Arrange
        when(activistRepository.existsById(TEST_ACTIVIST_ID)).thenReturn(true);
        when(activistRepository.findById(TEST_ACTIVIST_ID)).thenReturn(Optional.of(activistFixture));

        // Act
        ModificationType modificationType = activistServiceUnderTest.updateActivist(activistFixture);

        // Assert
        assertThat(modificationType, is(ModificationType.MODIFIED));
    }


    @Test
    @DisplayName("When modified Then its state should be created")
    public void givenANewActivist_whenModified_thenItsStateShouldHaveBeenCreated() {
        // Arrange
        when(activistRepository.existsById(TEST_ACTIVIST_ID)).thenReturn(false);

        // Act
        ModificationType modificationType = activistServiceUnderTest.updateActivist(activistFixture);

        // Assert
        assertThat(modificationType, is(ModificationType.CREATED));
    }

    private static Activist createActivistFixture(long activistId) {
        // Act
        Activist activist = new Activist();
        activist.setId(activistId);
        activist.setName("Fred Fstone");

        // Add address
        PostalAddress address = new PostalAddress();
        address.setStreetAddress("10 Prehistoric Lane");
        address.setZipCode("90210");
        activist.setAddress(address);
        return activist;
    }

}