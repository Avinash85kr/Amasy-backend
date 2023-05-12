package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.Center;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.CenterDto;
import com.amasy.gtbackend.payloads.CenterResponse;
import com.amasy.gtbackend.repositories.CenterRepo;
import com.amasy.gtbackend.repositories.TpUserRepo;
import com.amasy.gtbackend.services.CenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CenterServiceImpl implements CenterService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CenterRepo centerRepo;
    @Autowired
    private TpUserRepo tpUserRepo;
    @Override
    public CenterDto registerCenter(CenterDto centerDto, Integer tpUserId) {
        Center center = this.modelMapper.map(centerDto, Center.class);
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP USER", "ID", tpUserId));
        center.setGoAhead("default.xlsx");
        center.setCenStatus("Approved");
        center.setSnaStatus("Pending");
        center.setTpUser(tpUser);
        Center savedCenter = this.centerRepo.save(center);
        return this.modelMapper.map(savedCenter, CenterDto.class);
    }

    @Override
    public CenterDto updateCenter(CenterDto centerDto, Integer centerId) {
        Center center = this.centerRepo.findById(centerId).orElseThrow(() -> new ResourceNotFoundException("Center", "Id", centerId));
        center.setSnaStatus(centerDto.getSnaStatus());
        Center updatedCenter = this.centerRepo.save(center);
        return this.modelMapper.map(updatedCenter, CenterDto.class);
    }

    @Override
    public CenterResponse getAllCenters(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Center> pageCenters = this.centerRepo.findAll(pageable);
        List<Center> centers =pageCenters.getContent();
        List<CenterDto> centerDtos = centers.stream().map(center -> this.modelMapper.map(center, CenterDto.class)).collect(Collectors.toList());
        CenterResponse centerResponse = new CenterResponse();
        centerResponse.setContent(centerDtos);
        centerResponse.setPageNumber(pageCenters.getNumber());
        centerResponse.setPageSize(pageCenters.getSize());
        centerResponse.setTotalElements(pageCenters.getTotalElements());
        centerResponse.setTotalPages(pageCenters.getTotalPages());
        centerResponse.setLastPage(pageCenters.isLast());
        return  centerResponse;
    }

    @Override
    public void deleteCenter(Integer centerId) {
        Center center = this.centerRepo.findById(centerId).orElseThrow(() -> new ResourceNotFoundException("Center", "Id", centerId));
        this.centerRepo.delete(center);
    }

    @Override
    public List<CenterDto> getCentersByTpUserId(Integer tpUserId) {
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TpUser", "Id", tpUserId));
        List<Center> centers = this.centerRepo.getCentersByTpUser(tpUser);
        List<CenterDto> centerDtos = centers.stream().map(center -> this.modelMapper.map(center, CenterDto.class)).collect(Collectors.toList());
        return centerDtos;
    }
}
