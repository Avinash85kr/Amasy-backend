package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
