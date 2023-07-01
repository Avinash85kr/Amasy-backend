package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.entities.OrgCat;
import com.amasy.gtbackend.entities.Role;
import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.TpUserDto;
import com.amasy.gtbackend.payloads.TpUserResponse;
import com.amasy.gtbackend.repositories.OrgCatRepo;
import com.amasy.gtbackend.repositories.RoleRepo;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.repositories.TpUserRepo;
import com.amasy.gtbackend.services.TpUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TpUserServiceImpl implements TpUserService {

    @Autowired
    private TpUserRepo tpUserRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ScheCatRepo scheCatRepo;
    @Autowired
    private OrgCatRepo orgCatRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public TpUserDto registerNewTpUser(TpUserDto tpUserDto, Integer schId, Integer orgId) {
        SchemeCat schemeCat = this.scheCatRepo.findById(schId).orElseThrow(() -> new ResourceNotFoundException("Scheme", "Id", schId));
        OrgCat orgCat = this.orgCatRepo.findById(orgId).orElseThrow(() -> new ResourceNotFoundException("Organization", "Id", orgId));
        TpUser tpUser = this.modelMapper.map(tpUserDto, TpUser.class);
        Role role = this.roleRepo.findById(AppConstants.TP_USER).get();
        tpUser.setSchemeCategory(schemeCat);
        tpUser.setOrgCategory(orgCat);
        tpUser.getRoles().add(role);
        tpUser.setStatus("Pending");
        tpUser.setAddedDate(new Date());
        TpUser newTpUser = this.tpUserRepo.save(tpUser);
        return this.modelMapper.map(newTpUser, TpUserDto.class);
    }

    @Override
    public TpUserDto updateTpUser(TpUserDto tpUserDto, Integer tpUserId) {
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TpUser", "Id", tpUserId));
        SchemeCat schemeCat = this.scheCatRepo.findById(tpUserDto.getSchemeCategory().getSchemeId()).get();
        OrgCat orgCat = this.orgCatRepo.findById(tpUserDto.getOrgCategory().getOrgId()).get();
        tpUser.setSchemeCategory(schemeCat);
        tpUser.setOrgName(tpUserDto.getOrgName());
        tpUser.setOrgCategory(orgCat);
        tpUser.setAffiliation(tpUserDto.getAffiliation());
        tpUser.setRoaAddress(tpUserDto.getRoaAddress());
        tpUser.setRoaDist(tpUserDto.getRoaDist());
        tpUser.setRoaCity(tpUserDto.getRoaCity());
        tpUser.setRoaState(tpUserDto.getRoaState());
        tpUser.setRoaPin(tpUserDto.getRoaPin());
        tpUser.setRoaTelNo(tpUserDto.getRoaTelNo());
        tpUser.setRoaMobNo(tpUserDto.getRoaMobNo());
        tpUser.setRoaGst(tpUserDto.getRoaGst());
        tpUser.setSoaAddress(tpUserDto.getSoaAddress());
        tpUser.setSoaDist(tpUserDto.getSoaDist());
        tpUser.setSoaCity(tpUserDto.getSoaCity());
        tpUser.setSoaState(tpUserDto.getSoaState());
        tpUser.setSoaPin(tpUserDto.getSoaPin());
        tpUser.setSoaTelNo(tpUserDto.getSoaTelNo());
        tpUser.setSoaMobNo(tpUserDto.getSoaMobNo());
        tpUser.setSoaGst(tpUserDto.getSoaGst());
        tpUser.setWebsite(tpUserDto.getWebsite());
        tpUser.setPanCard(tpUserDto.getPanCard());
        tpUser.setPanNumber(tpUserDto.getPanNumber());
        tpUser.setHoName(tpUserDto.getHoName());
        tpUser.setHoQualification(tpUserDto.getHoQualification());
        tpUser.setHoDob(tpUserDto.getHoDob());
        tpUser.setHoExp(tpUserDto.getHoExp());
        tpUser.setHoCitizen(tpUserDto.getHoCitizen());
        tpUser.setHoPanNumber(tpUserDto.getHoPhNumber());
        tpUser.setHoResAddress(tpUserDto.getHoResAddress());
        tpUser.setHoPermAddress(tpUserDto.getHoPermAddress());
        tpUser.setHoPhNumber(tpUserDto.getHoPhNumber());
        tpUser.setHoAadharNumber(tpUserDto.getHoAadharNumber());
        tpUser.setHoAltAadharNumber(tpUserDto.getHoAltAadharNumber());
        tpUser.setHoEmail(tpUserDto.getHoEmail());
        tpUser.setHoPr1(tpUserDto.getHoPr1());
        tpUser.setHoPr2(tpUserDto.getHoPr2());
        tpUser.setHoPr3(tpUserDto.getHoPr3());
        tpUser.setPcName(tpUserDto.getPcName());
        tpUser.setPcDesignation(tpUserDto.getPcDesignation());
        tpUser.setPcResAddress(tpUserDto.getPcResAddress());
        tpUser.setPcPermAddress(tpUserDto.getPcPermAddress());
        tpUser.setPcCitizen(tpUserDto.getPcCitizen());
        tpUser.setPcPhNumber(tpUserDto.getPcPhNumber());
        tpUser.setPcAltPhNumber(tpUserDto.getPcAltPhNumber());
        tpUser.setPcEmail(tpUserDto.getPcEmail());
        tpUser.setPcAltEmail(tpUserDto.getPcAltEmail());
        tpUser.setUserName(tpUserDto.getUserName());
        tpUser.setPassword(this.passwordEncoder.encode(tpUserDto.getPassword()));
        tpUser.setStatus(tpUserDto.getStatus());
        TpUser updatedTpUser = this.tpUserRepo.save(tpUser);
        return this.modelMapper.map(updatedTpUser, TpUserDto.class);
    }

    @Override
    public TpUserDto getTpUserById(Integer tpUserId) {
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
        return mapper.map(tpUser, TpUserDto.class);
    }

    @Override
    public TpUserResponse getAllTpUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TpUser> pageTpUsers = this.tpUserRepo.findAll(pageable);
        List<TpUser> tpUsers = pageTpUsers.getContent();
        List<TpUserDto> tpUserDtos = tpUsers.stream().map(tpUser -> this.modelMapper.map(tpUser, TpUserDto.class)).collect(Collectors.toList());
        TpUserResponse tpUserResp = new TpUserResponse();
        tpUserResp.setPageNumber(pageTpUsers.getNumber());
        tpUserResp.setContent(tpUserDtos);
        tpUserResp.setPageSize(pageTpUsers.getSize());
        tpUserResp.setTotalElements(pageTpUsers.getTotalElements());
        tpUserResp.setTotalPages(pageTpUsers.getTotalPages());
        tpUserResp.setLastPage(pageTpUsers.isLast());
        return tpUserResp;
    }

    @Override
    public void deleteTpUser(Integer tpUserId) {
        TpUser user = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        this.tpUserRepo.delete(user);
    }
}
