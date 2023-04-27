package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.SrcUserDto;

import java.util.List;

public interface SrcUserService {
    SrcUserDto createSrcUser(SrcUserDto srcUserDto, Integer schId);
    SrcUserDto updateSrcUser(SrcUserDto srcUserDto, Integer srcId);
    SrcUserDto getSrcUserById(Integer srcId);
    List<SrcUserDto> getAllSrcUser();
    void deleteSrcUser(Integer srcId);
}
