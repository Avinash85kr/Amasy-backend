package com.amasy.gtbackend.services;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.payloads.BatchDto;
import com.amasy.gtbackend.payloads.BatchResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BatchService {
    void saveBatch(MultipartFile file, Integer centerId);
    BatchDto getBatchById(Integer batchId);
    List<BatchDto> getAllBatch();
    BatchDto updateBatch(BatchDto batchDto, Integer batchId);
    void deleteBatch(Batch batch, Integer batchId);
    List<BatchDto> getBatchByCenterId(Integer centerId);
    BatchResponse batchStatus(Integer centerId);
}
