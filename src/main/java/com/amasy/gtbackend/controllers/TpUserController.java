package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.TpUserDto;
import com.amasy.gtbackend.payloads.TpUserResponse;
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
    @PostMapping("/schemes/{schId}/orgs/{orgId}")
    public ResponseEntity<TpUserDto> createTp(@Valid @RequestBody TpUserDto tpUserDto, @PathVariable Integer schId, @PathVariable Integer orgId){
        TpUserDto savedUser = this.tpUserService.registerNewTpUser(tpUserDto, schId, orgId);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PutMapping("/{tpUserId}")
    public ResponseEntity<TpUserDto> updateTp(@Valid @RequestBody TpUserDto tpUserDto, @PathVariable Integer tpUserId){
        TpUserDto updateUser = this.tpUserService.updateTpUser(tpUserDto, tpUserId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @GetMapping("/{tpUserId}")
    public ResponseEntity<TpUserDto> getUser(@PathVariable Integer tpUserId){
        TpUserDto tpUserById = this.tpUserService.getTpUserById(tpUserId);
        return ResponseEntity.ok(tpUserById);
    }
    @GetMapping("/")
    public ResponseEntity<TpUserResponse> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "id", required = false)String sortBy,
                                                      @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION, required = false)String sortDirection){
        TpUserResponse allTpUsers = this.tpUserService.getAllTpUser(pageNumber, pageSize, sortBy, sortDirection);
        return ResponseEntity.ok(allTpUsers);
    }
    @DeleteMapping("/{tpUserId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer tpUserId){
        this.tpUserService.deleteTpUser(tpUserId);
        return new ResponseEntity<>(new ApiResponse("Tp user deleted successfully !!", true), HttpStatus.OK);
    }
}
