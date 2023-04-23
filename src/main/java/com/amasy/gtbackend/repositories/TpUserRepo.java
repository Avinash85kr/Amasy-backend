package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.TpUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TpUserRepo extends JpaRepository<TpUser, Integer> {
}
