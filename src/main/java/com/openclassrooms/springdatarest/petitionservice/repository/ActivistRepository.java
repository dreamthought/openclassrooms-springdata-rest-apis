package com.openclassrooms.springdatarest.petitionservice.repository;

import com.openclassrooms.springdatarest.petitionservice.domain.Activist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivistRepository extends JpaRepository<Activist, Long> {
}
