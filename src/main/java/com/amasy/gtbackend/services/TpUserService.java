package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.TpUserDto;

import java.util.List;

public interface TpUserService {
    TpUserDto registerNewTpUser(TpUserDto tpUserDto, Integer schId, Integer orgId);
    TpUserDto updateTpUser(TpUserDto tpUserDto, Integer tpUserId);
    TpUserDto getTpUserById(Integer tpUserId);
    List<TpUserDto> getAllTpUser();
    void deleteTpUser(Integer tpUserId);
}
