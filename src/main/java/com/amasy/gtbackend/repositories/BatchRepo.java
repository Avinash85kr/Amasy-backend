package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepo extends JpaRepository<Batch, Integer> {
    List<Batch> findBatchByCenter(Center center);
}
