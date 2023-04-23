package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.SchCatDto;
import com.amasy.gtbackend.services.ScheCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schemes")
public class SchController {
    @Autowired
    private ScheCatService scheCatService;
    @PostMapping("/")
    public ResponseEntity<SchCatDto> createScheme(@Valid @RequestBody SchCatDto schCatDto){
        SchCatDto createSchCatDto = this.scheCatService.createScheme(schCatDto);
        return new ResponseEntity<>(createSchCatDto, HttpStatus.CREATED);
    }
    @PutMapping("/{schemeId}")
    public ResponseEntity<SchCatDto> updateScheme(@Valid @RequestBody SchCatDto schCatDto, @PathVariable Integer schemeId){
        SchCatDto updatedScheme = this.scheCatService.updateScheme(schCatDto, schemeId);
        return new ResponseEntity<>(updatedScheme, HttpStatus.OK);
    }
    @GetMapping("/{schemeId}")
    public ResponseEntity<SchCatDto> getSchemeById(@PathVariable Integer schemeId){
        SchCatDto getScheme = this.scheCatService.getSchemeById(schemeId);
        return ResponseEntity.ok(getScheme);
    }
    @GetMapping("/")
    public ResponseEntity<List<SchCatDto>> getAllSchemes(){
        List<SchCatDto> allSchemes = this.scheCatService.getAllSchemes();
        return ResponseEntity.ok(allSchemes);
    }
    @DeleteMapping("/{schemeId}")
    public ResponseEntity<ApiResponse> deleteSch(@PathVariable Integer schemeId){
        this.scheCatService.deleteScheme(schemeId);
        return new ResponseEntity<>(new ApiResponse("Scheme deleted successfully !!", true), HttpStatus.OK);
    }
}
