package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ApiException;
import com.amasy.gtbackend.payloads.JwtAuthenticationRequest;
import com.amasy.gtbackend.payloads.JwtAuthenticationResponse;
import com.amasy.gtbackend.payloads.TpUserDto;
import com.amasy.gtbackend.payloads.SrcUserDto;
import com.amasy.gtbackend.repositories.TpUserRepo;
import com.amasy.gtbackend.security.JwtTokenHelper;
import com.amasy.gtbackend.services.TpUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TpUserService tpUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TpUserRepo tpUserRepo;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest request) {
        this.authenticate(request.getUserName(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(token);
        if (this.tpUserRepo.findByUserName(request.getUserName()).isPresent()) response.setTpUser(this.modelMapper.map((TpUser)userDetails, TpUserDto.class));
        else response.setSrcUser(this.modelMapper.map((SrcUser)userDetails, SrcUserDto.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException exception) {
            throw new ApiException("Incorrect username or password !!");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<TpUserDto> register(@Valid @RequestBody TpUserDto tpUSerDto){
        TpUserDto registerUser = this.tpUserService.registerNewTpUser(tpUSerDto);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }
}
