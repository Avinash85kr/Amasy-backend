package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.SchemeCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheCatRepo extends JpaRepository<SchemeCat, Integer> {
}
