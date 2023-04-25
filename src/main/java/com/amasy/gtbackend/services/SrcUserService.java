package com.amasy.gtbackend.services;

import com.amasy.gtbackend.entities.SrcUser;

import java.util.List;

public interface SrcUserService {
    SrcUser createSrcUser(SrcUser srcUser);
    SrcUser updateSrcUser(SrcUser srcUser, Integer srcId);
    SrcUser getSrcUserById(Integer srcId);
    List<SrcUser> getAllSrcUser();
    void deleteSrcUser(Integer srcId);
}
