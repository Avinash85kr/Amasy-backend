package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.entities.Role;
import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.SrcUserDto;
import com.amasy.gtbackend.repositories.RoleRepo;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.repositories.SrcUserRepo;
import com.amasy.gtbackend.services.SrcUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SrcUserServiceImpl implements SrcUserService {
    @Autowired
    private SrcUserRepo srcUserRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ScheCatRepo scheCatRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public SrcUserDto createSrcUser(SrcUserDto srcUserDto) {
        SrcUser srcUser = this.modelMapper.map(srcUserDto, SrcUser.class);
        srcUser.setPassword(this.passwordEncoder.encode(srcUser.getPassword()));
        Role role = this.roleRepo.findById(AppConstants.SRC_USER).get();
        srcUser.getRoles().add(role);
        SrcUser user = this.srcUserRepo.save(srcUser);
        return this.modelMapper.map(user, SrcUserDto.class);
    }

    @Override
    public SrcUserDto updateSrcUser(SrcUserDto srcUserDto, Integer srcId) {
        SrcUser user = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        SchemeCat scheme = this.scheCatRepo.findById(srcUserDto.getSchemeCat().getSchemeId()).get();
        user.setName(srcUserDto.getName());
        user.setPhNum(srcUserDto.getPhNum());
        user.setOprState(srcUserDto.getOprState());
        user.setSchemeCat(scheme);
        user.setUserName(srcUserDto.getUserName());
        user.setPassword(srcUserDto.getPassword());
        SrcUser savedUser = this.srcUserRepo.save(user);
        return this.modelMapper.map(savedUser, SrcUserDto.class);
    }

    @Override
    public SrcUserDto getSrcUserById(Integer srcId) {
        SrcUser getUser = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        return this.modelMapper.map(getUser, SrcUserDto.class);
    }

    @Override
    public List<SrcUserDto> getAllSrcUser() {
        List<SrcUser> users = this.srcUserRepo.findAll();
        List<SrcUserDto> srcUserDtos = users.stream().map(user -> this.modelMapper.map(user, SrcUserDto.class)).collect(Collectors.toList());
        return srcUserDtos;
    }

    @Override
    public void deleteSrcUser(Integer srcId) {
        SrcUser user = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        this.srcUserRepo.delete(user);
    }
}
