package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.SrcUserDto;
import com.amasy.gtbackend.services.SrcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/srcUsers")
public class SrcUSerController {
    @Autowired
    private SrcUserService srcUserService;
    @PostMapping("/schemeId/{schId}")
    public ResponseEntity<SrcUserDto> createSrcUser(@Valid @RequestBody SrcUserDto srcUser, @PathVariable Integer schId){
        SrcUserDto savedUser = this.srcUserService.createSrcUser(srcUser, schId);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/{srcId}")
    public ResponseEntity<SrcUserDto> updateSrcUser(@Valid @RequestBody SrcUserDto srcUser, @PathVariable Integer srcId) {
        SrcUserDto updateUser = this.srcUserService.updateSrcUser(srcUser, srcId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @GetMapping("/{srcId}")
    public ResponseEntity<SrcUserDto> getSrcUserById(@PathVariable Integer srcId){
        SrcUserDto getUserById = this.srcUserService.getSrcUserById(srcId);
        return ResponseEntity.ok(getUserById);
    }
    @GetMapping("/")
    public ResponseEntity<List<SrcUserDto>> getAllSrcUser(){
        List<SrcUserDto> allUsers = this.srcUserService.getAllSrcUser();
        return ResponseEntity.ok(allUsers);
    }
    @DeleteMapping("/{srcId}")
    public ResponseEntity<ApiResponse> deleteSrcUser(@PathVariable Integer srcId){
        this.srcUserService.deleteSrcUser(srcId);
        return new ResponseEntity<>(new ApiResponse("SRC User deleted successfully !!", true), HttpStatus.OK);
    }
}
