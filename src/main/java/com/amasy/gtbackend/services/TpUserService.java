package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.TpUserDto;
import com.amasy.gtbackend.payloads.TpUserResponse;

import java.util.List;

public interface TpUserService {
    TpUserDto registerNewTpUser(TpUserDto tpUserDto, Integer schId, Integer orgId);
    TpUserDto updateTpUser(TpUserDto tpUserDto, Integer tpUserId);
    TpUserDto getTpUserById(Integer tpUserId);
    TpUserResponse getAllTpUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    void deleteTpUser(Integer tpUserId);
}
