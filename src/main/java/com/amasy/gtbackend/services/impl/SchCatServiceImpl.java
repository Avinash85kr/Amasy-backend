package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.SchCatDto;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.services.ScheCatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchCatServiceImpl implements ScheCatService {
    @Autowired
    private ScheCatRepo scheCatRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SchCatDto createScheme(SchCatDto schCatDto) {
        SchemeCat schemeCat = this.modelMapper.map(schCatDto, SchemeCat.class);
        SchemeCat savedScheme = this.scheCatRepo.save(schemeCat);
        return this.modelMapper.map(savedScheme, SchCatDto.class);
    }

    @Override
    public SchCatDto updateScheme(SchCatDto schCatDto, Integer schemeId) {
        SchemeCat schemeCat = this.scheCatRepo.findById(schemeId).orElseThrow(() -> new ResourceNotFoundException("Scheme", "Id", schemeId));
        schemeCat.setSchemeName(schCatDto.getSchemeName());
        SchemeCat savedScheme = this.scheCatRepo.save(schemeCat);
        return this.modelMapper.map(savedScheme, SchCatDto.class);
    }

    @Override
    public SchCatDto getSchemeById(Integer schemeId) {
        SchemeCat schemeCat = this.scheCatRepo.findById(schemeId).orElseThrow(() -> new ResourceNotFoundException("Scheme", "Id", schemeId));
        return this.modelMapper.map(schemeCat, SchCatDto.class);
    }

    @Override
    public List<SchCatDto> getAllSchemes() {
        List<SchemeCat> schemes = this.scheCatRepo.findAll();
        List<SchCatDto> schemeDtos = schemes.stream().map(schemeCat -> this.modelMapper.map(schemeCat, SchCatDto.class)).collect(Collectors.toList());
        return schemeDtos;
    }

    @Override
    public void deleteScheme(Integer schemeId) {
        SchemeCat schemeCat = this.scheCatRepo.findById(schemeId).orElseThrow(() -> new ResourceNotFoundException("Scheme", "Id", schemeId));
        this.scheCatRepo.delete(schemeCat);
    }
}
