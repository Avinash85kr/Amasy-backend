package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.payloads.ApiResponse;
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
    @PostMapping("/")
    public ResponseEntity<SrcUser> createSrcUser(@Valid @RequestBody SrcUser srcUser){
        SrcUser savedUser = this.srcUserService.createSrcUser(srcUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/{srcId}")
    public ResponseEntity<SrcUser> updateSrcUser(@Valid @RequestBody SrcUser srcUser, @PathVariable Integer srcId) {
        SrcUser updateUser = this.srcUserService.updateSrcUser(srcUser, srcId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @GetMapping("/{srcId}")
    public ResponseEntity<SrcUser> getSrcUserById(@PathVariable Integer srcId){
        SrcUser getUserById = this.srcUserService.getSrcUserById(srcId);
        return ResponseEntity.ok(getUserById);
    }
    @GetMapping("/")
    public ResponseEntity<List<SrcUser>> getAllSrcUser(){
        List<SrcUser> allUsers = this.srcUserService.getAllSrcUser();
        return ResponseEntity.ok(allUsers);
    }
    @DeleteMapping("/{srcId}")
    public ResponseEntity<ApiResponse> deleteSrcUser(@PathVariable Integer srcId){
        this.srcUserService.deleteSrcUser(srcId);
        return new ResponseEntity<>(new ApiResponse("SRC User deleted successfully !!", true), HttpStatus.OK);
    }
}
