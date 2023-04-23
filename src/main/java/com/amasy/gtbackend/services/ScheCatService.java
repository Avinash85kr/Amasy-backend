package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.SchCatDto;

import java.util.List;

public interface ScheCatService {
    SchCatDto createScheme(SchCatDto schCatDto);
    SchCatDto updateScheme(SchCatDto schCatDto, Integer schemeId);
    SchCatDto getSchemeById(Integer schemeId);
    List<SchCatDto> getAllSchemes();
    void deleteScheme(Integer schemeId);
}
