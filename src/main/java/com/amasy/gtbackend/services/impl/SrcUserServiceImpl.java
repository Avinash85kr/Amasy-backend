package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.repositories.SrcUserRepo;
import com.amasy.gtbackend.services.SrcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SrcUserServiceImpl implements SrcUserService {
    @Autowired
    private SrcUserRepo srcUserRepo;
    @Autowired
    private ScheCatRepo scheCatRepo;
    @Override
    public SrcUser createSrcUser(SrcUser srcUser) {
        SrcUser user = this.srcUserRepo.save(srcUser);
        return user;
    }

    @Override
    public SrcUser updateSrcUser(SrcUser srcUser, Integer srcId) {
        SrcUser user = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        SchemeCat scheme = this.scheCatRepo.findById(user.getSchemeCat().getSchemeId()).get();
        user.setName(user.getName());
        user.setPhNum(user.getPhNum());
        user.setOprState(user.getOprState());
        user.setSchemeCat(scheme);
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        SrcUser savedUser = this.srcUserRepo.save(user);
        return savedUser;
    }

    @Override
    public SrcUser getSrcUserById(Integer srcId) {
        SrcUser getUser = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        return getUser;
    }

    @Override
    public List<SrcUser> getAllSrcUser() {
        return this.srcUserRepo.findAll();
    }

    @Override
    public void deleteSrcUser(Integer srcId) {
        SrcUser user = this.srcUserRepo.findById(srcId).orElseThrow(() -> new ResourceNotFoundException("SRC User", "srcId", srcId));
        this.srcUserRepo.delete(user);
    }
}
