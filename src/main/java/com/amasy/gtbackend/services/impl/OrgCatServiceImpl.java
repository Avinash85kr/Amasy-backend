package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.OrgCat;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.OrgCatDto;
import com.amasy.gtbackend.repositories.OrgCatRepo;
import com.amasy.gtbackend.services.OrgCatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgCatServiceImpl implements OrgCatService {
    @Autowired
    private OrgCatRepo orgCatRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrgCatDto createOrg(OrgCatDto orgCatDto) {
        OrgCat orgCat = this.modelMapper.map(orgCatDto, OrgCat.class);
        OrgCat savedOrg = this.orgCatRepo.save(orgCat);
        return this.modelMapper.map(savedOrg, OrgCatDto.class);
    }

    @Override
    public OrgCatDto updateOrg(OrgCatDto orgCatDto, Integer orgId) {
        OrgCat orgCat = this.orgCatRepo.findById(orgId).orElseThrow(() -> new ResourceNotFoundException("Organization", "Id", orgId));
        orgCat.setOrgType(orgCatDto.getOrgType());
        OrgCat updateOrgCat = this.orgCatRepo.save(orgCat);
        return this.modelMapper.map(updateOrgCat, OrgCatDto.class);
    }

    @Override
    public OrgCatDto getOrgById(Integer orgId) {
        OrgCat orgCat = this.orgCatRepo.findById(orgId).orElseThrow(() -> new ResourceNotFoundException("Organization", "Id", orgId));
        return this.modelMapper.map(orgCat, OrgCatDto.class);
    }

    @Override
    public List<OrgCatDto> getAllOrg() {
        List<OrgCat> organizations = this.orgCatRepo.findAll();
        List<OrgCatDto> orgCatDtos = organizations.stream().map(orgCat -> this.modelMapper.map(orgCat, OrgCatDto.class)).collect(Collectors.toList());
        return orgCatDtos;
    }

    @Override
    public void deleteOrg(Integer orgId) {
        OrgCat orgCat = this.orgCatRepo.findById(orgId).orElseThrow(() -> new ResourceNotFoundException("Organization", "Id", orgId));
        this.orgCatRepo.delete(orgCat);
    }
}
