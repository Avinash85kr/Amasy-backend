package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.CenterDto;
import com.amasy.gtbackend.payloads.CenterResponse;

import java.util.List;


public interface CenterService {
    CenterDto registerCenter(CenterDto centerDto, Integer tpUserId);
    CenterDto updateCenter(CenterDto centerDto, Integer centerId);
    CenterResponse getAllCenters(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    void deleteCenter(Integer centerId);
    List<CenterDto> getCentersByTpUserId(Integer tpUserId);
}
