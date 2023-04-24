package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.services.TpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tpUsers")
public class TpUserController {
    @Autowired
    private TpUserService tpUserService;
    @PostMapping("/")
    public ResponseEntity<TpUser> createTp(@Valid @RequestBody TpUser tpUser){
        TpUser savedUser = this.tpUserService.createTpUser(tpUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/{tpUserId}")
    public ResponseEntity<TpUser> updateTp(@Valid @RequestBody TpUser tpUser, @PathVariable Integer tpUserId){
        TpUser updateUser = this.tpUserService.updateTpUser(tpUser, tpUserId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @GetMapping("/{tpUserId}")
    public ResponseEntity<TpUser> getUser(@PathVariable Integer tpUserId){
        TpUser tpUserById = this.tpUserService.getTpUserById(tpUserId);
        return ResponseEntity.ok(tpUserById);
    }
    @GetMapping("/")
    public ResponseEntity<List<TpUser>> getAllUsers(){
        List<TpUser> getAllUsers = this.tpUserService.getAllTpUser();
        return ResponseEntity.ok(getAllUsers);
    }
    @DeleteMapping("/{tpUserId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer tpUserId){
        this.tpUserService.deleteTpUser(tpUserId);
        return new ResponseEntity<>(new ApiResponse("Tp user deleted successfully !!", true), HttpStatus.OK);
    }
}
