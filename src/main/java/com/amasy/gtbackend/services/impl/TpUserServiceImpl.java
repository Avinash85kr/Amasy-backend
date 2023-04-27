package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.config.AppConstants;
import com.amasy.gtbackend.entities.OrgCat;
import com.amasy.gtbackend.entities.Role;
import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.OrgCatDto;
import com.amasy.gtbackend.payloads.RoleDto;
import com.amasy.gtbackend.payloads.SchCatDto;
import com.amasy.gtbackend.payloads.TpUserDto;
import com.amasy.gtbackend.repositories.OrgCatRepo;
import com.amasy.gtbackend.repositories.RoleRepo;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.repositories.TpUserRepo;
import com.amasy.gtbackend.services.TpUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public TpUserDto registerNewTpUser(TpUserDto tpUserDto) {
        TpUser tpUser = this.modelMapper.map(tpUserDto, TpUser.class);
        tpUser.setPassword(this.passwordEncoder.encode(tpUser.getPassword()));
        Role role = this.roleRepo.findById(AppConstants.TP_USER).get();
        tpUser.getRoles().add(role);
        TpUser newTpUser = this.tpUserRepo.save(tpUser);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(newTpUser, TpUserDto.class);
    }

    @Override
    public TpUserDto createTpUser(TpUserDto tpUserDto) {
        TpUser tpUser = this.modelMapper.map(tpUserDto, TpUser.class);
        TpUser savedTpUser = this.tpUserRepo.save(tpUser);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(savedTpUser, TpUserDto.class);
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
        tpUser.setPassword(tpUserDto.getPassword());
        TpUser updatedTpUser = this.tpUserRepo.save(tpUser);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(updatedTpUser, TpUserDto.class);
    }

    @Override
    public TpUserDto getTpUserById(Integer tpUserId) {
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(tpUser, TpUserDto.class);
    }

    @Override
    public List<TpUserDto> getAllTpUser() {
        List<TpUser> tpUsers = this.tpUserRepo.findAll();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        List<TpUserDto> tpUserDtos = tpUsers.stream().map(tpUser -> mapper.map(tpUser, TpUserDto.class)).collect(Collectors.toList());
        return tpUserDtos;
    }

    @Override
    public void deleteTpUser(Integer tpUserId) {
        TpUser user = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        this.tpUserRepo.delete(user);
    }
}
