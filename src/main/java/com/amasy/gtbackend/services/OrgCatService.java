package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.OrgCatDto;

import java.util.List;

public interface OrgCatService {
    OrgCatDto createOrg(OrgCatDto orgCatDto);
    OrgCatDto updateOrg(OrgCatDto orgCatDto, Integer orgId);
    OrgCatDto getOrgById(Integer orgId);
    List<OrgCatDto> getAllOrg();
    void deleteOrg(Integer orgId);
}
