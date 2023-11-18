package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.entities.Center;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.helper.ExcelHelper;
import com.amasy.gtbackend.payloads.BatchDto;
import com.amasy.gtbackend.payloads.BatchResponse;
import com.amasy.gtbackend.repositories.BatchRepo;
import com.amasy.gtbackend.repositories.CenterRepo;
import com.amasy.gtbackend.services.BatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchRepo batchRepo;
    @Autowired
    private CenterRepo centerRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveBatch(MultipartFile file, Integer centerId) {
        try{
            ExcelHelper excelHelper = new ExcelHelper();
            List<Batch> batches = excelHelper.convertExcelToListOfProduct(file.getInputStream(), centerId);
            this.batchRepo.saveAll(batches);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BatchDto getBatchById(Integer batchId) {
        Batch batch = this.batchRepo.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch", "Id", batchId));
        return this.modelMapper.map(batch, BatchDto.class);
    }

    @Override
    public List<BatchDto> getAllBatch() {
        List<Batch> batches = this.batchRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BatchDto> batchDtos = batches.stream().map((batch) -> this.modelMapper.map(batch, BatchDto.class)).collect(Collectors.toList());
        return batchDtos;
    }

    @Override
    public BatchDto updateBatch(BatchDto batchDto, Integer batchId) {
        Batch batch = this.batchRepo.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch", "Id", batchId));
        batch.setPhoto("default.png");
        batch.setStatus(batchDto.getStatus());
        Batch saveBatch = this.batchRepo.save(batch);
        return this.modelMapper.map(saveBatch, BatchDto.class);
    }

    @Override
    public void deleteBatch(Batch batch, Integer batchId) {
        Batch batch1 = this.batchRepo.findById(batchId).orElseThrow(() -> new ResourceNotFoundException("Batch", "Id", batchId));
        this.batchRepo.delete(batch1);
    }

    @Override
    public List<BatchDto> getBatchByCenterId(Integer centerId) {
        Center center = this.centerRepo.findById(centerId).orElseThrow(() -> new ResourceNotFoundException("Center", "Id", centerId));
        List<Batch> batchByCenter = this.batchRepo.findBatchByCenter(center);
        List<BatchDto> batchDtos = batchByCenter.stream().map(batch -> this.modelMapper.map(batch, BatchDto.class)).collect(Collectors.toList());
        return batchDtos;
    }

    @Override
    public BatchResponse batchStatus(Integer centerId) {
        Center center = this.centerRepo.findById(centerId).orElseThrow(() -> new ResourceNotFoundException("Center", "Id", centerId));
        List<Batch> batches = this.batchRepo.findBatchByCenter(center);
        BatchResponse batchResponse = new BatchResponse();
            List<Batch> pendingBatches = this.batchRepo.pendingBatch(center);
            List<Batch> ongoingBatch = this.batchRepo.ongoingBatch(center);
            List<Batch> completeBatch = this.batchRepo.completeBatch(center);
            List<BatchDto> batchDtos = batches.stream().map((batch) -> this.modelMapper.map(batch, BatchDto.class)).collect(Collectors.toList());
            batchResponse.setContent(batchDtos);
            batchResponse.setTotalElements(batches.size());
            batchResponse.setPending(pendingBatches.size());
            batchResponse.setProjectId(center.getCenterPrId());
            batchResponse.setCenterId(centerId);
            batchResponse.setCenterName(center.getCenName());
            batchResponse.setOngoingBatches(ongoingBatch.size());
            batchResponse.setCompletedBatches(completeBatch.size());
        return batchResponse;
    }
}
