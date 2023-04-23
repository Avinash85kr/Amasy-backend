package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.OrgCatDto;
import com.amasy.gtbackend.services.OrgCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orgs")
public class OrgController {
    @Autowired
    private OrgCatService orgCatService;
    @PostMapping("/")
    public ResponseEntity<OrgCatDto> createOrg(@Valid @RequestBody OrgCatDto orgCatDto){
        OrgCatDto savedOrg = this.orgCatService.createOrg(orgCatDto);
        return new ResponseEntity<>(savedOrg, HttpStatus.CREATED);
    }
    @PutMapping("/{orgId}")
    public ResponseEntity<OrgCatDto> updateOrg(@Valid @RequestBody OrgCatDto orgCatDto, @PathVariable Integer orgId){
        OrgCatDto orgCatDto1 = this.orgCatService.updateOrg(orgCatDto, orgId);
        return new ResponseEntity<>(orgCatDto1, HttpStatus.OK);
    }
    @GetMapping("/{orgId}")
    public ResponseEntity<OrgCatDto> getOrgById(@PathVariable Integer orgId){
        OrgCatDto orgById = this.orgCatService.getOrgById(orgId);
        return ResponseEntity.ok(orgById);
    }
    @GetMapping("/")
    public ResponseEntity<List<OrgCatDto>> getAllOrg(){
        List<OrgCatDto> allOrg = this.orgCatService.getAllOrg();
        return ResponseEntity.ok(allOrg);
    }
    @DeleteMapping("/{orgId}")
    public ResponseEntity<ApiResponse> deleteOrg(@PathVariable Integer orgId){
        this.orgCatService.deleteOrg(orgId);
        return new ResponseEntity<>(new ApiResponse("Organization deleted successfully !!", true), HttpStatus.OK);
    }
}
