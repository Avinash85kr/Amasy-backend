package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.TpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TpUserRepo extends JpaRepository<TpUser, Integer> {
    Optional<TpUser> findByUserName(String userName);
}
