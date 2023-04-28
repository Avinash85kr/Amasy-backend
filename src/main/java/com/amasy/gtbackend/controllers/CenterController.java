package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.CenterDto;
import com.amasy.gtbackend.payloads.CenterResponse;
import com.amasy.gtbackend.services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CenterController {
    @Autowired
    private CenterService centerService;
    @PreAuthorize("hasAuthority('TP_USER')")
    @PostMapping("/tpUsers/{tpUserId}/centers/")
    public ResponseEntity<CenterDto> createCenter(@Valid @RequestBody CenterDto centerDto, @PathVariable Integer tpUserId){
        CenterDto createdCenter = this.centerService.registerCenter(centerDto, tpUserId);
        return new ResponseEntity<>(createdCenter, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SRC_USER')")
    @PutMapping("/centers/{centerId}")
    public ResponseEntity<CenterDto> updateCenter(@Valid @RequestBody CenterDto centerDto, @PathVariable Integer centerId){
        CenterDto updated = this.centerService.updateCenter(centerDto, centerId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('SRC_USER', 'TP_USER')")
    @GetMapping("/centers/")
    public ResponseEntity<CenterResponse> getAllCenter(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,
                                                        @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false)String sortDirection){
        CenterResponse centerResponse = this.centerService.getAllCenters(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(centerResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('TP_USER')")
    @DeleteMapping("/centers/{centerId}")
    public ResponseEntity<ApiResponse> deleteCen(@PathVariable Integer centerId){
        this.centerService.deleteCenter(centerId);
        return new ResponseEntity<>(new ApiResponse("Center deleted successfully !!", true), HttpStatus.OK);
    }
}