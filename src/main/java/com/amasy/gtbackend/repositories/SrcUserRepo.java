package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.SrcUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SrcUserRepo extends JpaRepository<SrcUser, Integer> {
}
