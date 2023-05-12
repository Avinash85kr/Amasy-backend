package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.Center;
import com.amasy.gtbackend.entities.TpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenterRepo extends JpaRepository<Center, Integer> {
    List<Center> getCentersByTpUser(TpUser tpUser);
}
