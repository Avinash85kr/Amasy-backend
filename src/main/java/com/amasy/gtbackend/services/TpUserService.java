package com.amasy.gtbackend.services;

import com.amasy.gtbackend.entities.TpUser;

import java.util.List;

public interface TpUserService {
    TpUser registerNewTpUser(TpUser tpUser);
    TpUser createTpUser(TpUser tpUser);
    TpUser updateTpUser(TpUser tpUser, Integer tpUserId);
    TpUser getTpUserById(Integer tpUserId);
    List<TpUser> getAllTpUser();
    void deleteTpUser(Integer tpUserId);
}
