package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.SrcUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SrcUserRepo extends JpaRepository<SrcUser, Integer> {
    Optional<SrcUser> findByUserName(String userName);
}
