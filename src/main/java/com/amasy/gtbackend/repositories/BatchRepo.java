package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepo extends JpaRepository<Batch, Integer> {
    List<Batch> findBatchByCenter(Center center);
    @Query("select b from Batch b where b.status = 'pending' and b.center = :key")
    List<Batch> pendingBatch(@Param("key") Center center);
    @Query("select b from Batch b where b.status = 'ongoing' and b.center = :key")
    List<Batch> ongoingBatch(@Param("key") Center center);
    @Query("select b from Batch b where b.status = 'complete' and b.center = :key")
    List<Batch> completeBatch(@Param("key") Center center);
}
