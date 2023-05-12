package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.entities.Batch;
import com.amasy.gtbackend.helper.ExcelHelper;
import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.BatchDto;
import com.amasy.gtbackend.services.BatchService;
import com.amasy.gtbackend.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BatchController {
    @Autowired
    private BatchService batchService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/batch/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @PathVariable Integer centerId){
        if (ExcelHelper.checkExcelFormat(file)) {
            this.batchService.saveBatch(file, centerId);
            return ResponseEntity.ok(Map.of("message", "File is uploaded !"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file only !");
    }

    @GetMapping("/batches")
    public ResponseEntity<List<BatchDto>> getBatches(){
        List<BatchDto> allBatch = this.batchService.getAllBatch();
        return new ResponseEntity<>(allBatch, HttpStatus.OK);
    }

    @PutMapping("/batch/photo/upload/{batchId}")
    public ResponseEntity<BatchDto> uploadPhoto(@RequestParam("photo")MultipartFile photo, @PathVariable Integer batchId) throws IOException {
        BatchDto batch = this.batchService.getBatchById(batchId);
        String fileName = this.fileService.uploadImage(path, photo);
        batch.setPhoto(fileName);
        BatchDto updateBatch = this.batchService.updateBatch(batch, batchId);
        return new ResponseEntity<>(updateBatch, HttpStatus.OK);
    }

    @DeleteMapping("/batches/{batchId}")
    public ResponseEntity<ApiResponse> deleteBatch(@RequestBody Batch batch, @PathVariable Integer batchId){
        this.batchService.deleteBatch(batch, batchId);
        return new ResponseEntity<>(new ApiResponse("Batch deleted successfully !", true), HttpStatus.OK);
    }

    @GetMapping("/batches/centers/{centerId}")
    public ResponseEntity<List<BatchDto>> getBatch(@PathVariable Integer centerId){
        List<BatchDto> batchesByCenterId = this.batchService.getBatchByCenterId(centerId);
        return new ResponseEntity<>(batchesByCenterId, HttpStatus.OK);
    }
}
