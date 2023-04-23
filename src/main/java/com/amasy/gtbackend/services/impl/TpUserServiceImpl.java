package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.OrgCat;
import com.amasy.gtbackend.entities.SchemeCat;
import com.amasy.gtbackend.entities.TpUser;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.repositories.OrgCatRepo;
import com.amasy.gtbackend.repositories.ScheCatRepo;
import com.amasy.gtbackend.repositories.TpUserRepo;
import com.amasy.gtbackend.services.TpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TpUserServiceImpl implements TpUserService {

    @Autowired
    private TpUserRepo tpUserRepo;
    @Autowired
    private ScheCatRepo scheCatRepo;
    @Autowired
    private OrgCatRepo orgCatRepo;

    @Override
    public TpUser createTpUser(TpUser tpUser) {
        TpUser savedTpUser = this.tpUserRepo.save(tpUser);
        return savedTpUser;
    }

    @Override
    public TpUser updateTpUser(TpUser tpUser, Integer tpUserId) {
        tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TpUser", "Id", tpUserId));
        SchemeCat schemeCat = this.scheCatRepo.findById(tpUser.getSchemeCategory().getSchemeId()).get();
        OrgCat orgCat = this.orgCatRepo.findById(tpUser.getOrgCategory().getOrgId()).get();
        tpUser.setSchemeCategory(schemeCat);
        tpUser.setOrgName(tpUser.getOrgName());
        tpUser.setOrgCategory(orgCat);
        tpUser.setAffiliation(tpUser.getAffiliation());
        tpUser.setRoaAddress(tpUser.getRoaAddress());
        tpUser.setRoaDist(tpUser.getRoaDist());
        tpUser.setRoaState(tpUser.getRoaState());
        tpUser.setRoaPIN(tpUser.getRoaPIN());
        tpUser.setRoaTelNo(tpUser.getRoaTelNo());
        tpUser.setRoaMobNo(tpUser.getRoaMobNo());
        tpUser.setRoaGST(tpUser.getRoaGST());
        tpUser.setSoaAddress(tpUser.getSoaAddress());
        tpUser.setSoaDist(tpUser.getSoaDist());
        tpUser.setSoaState(tpUser.getSoaState());
        tpUser.setSoaPIN(tpUser.getSoaPIN());
        tpUser.setSoaTelNo(tpUser.getSoaTelNo());
        tpUser.setSoaMobNo(tpUser.getSoaMobNo());
        tpUser.setSoaGST(tpUser.getSoaGST());
        tpUser.setWebsite(tpUser.getWebsite());
        tpUser.setPanCard(tpUser.getPanCard());
        tpUser.setPanNumber(tpUser.getPanNumber());
        tpUser.setHoName(tpUser.getHoName());
        tpUser.setHoQualification(tpUser.getHoQualification());
        tpUser.setHoDOB(tpUser.getHoDOB());
        tpUser.setHoExp(tpUser.getHoExp());
        tpUser.setHoCitizen(tpUser.getHoCitizen());
        tpUser.setHoPanNumber(tpUser.getHoPhNumber());
        tpUser.setHoResAddress(tpUser.getHoResAddress());
        tpUser.setHoPermAddress(tpUser.getHoPermAddress());
        tpUser.setHoPhNumber(tpUser.getHoPhNumber());
        tpUser.setHoAadharNumber(tpUser.getHoAadharNumber());
        tpUser.setHoAltAadharNumber(tpUser.getHoAltAadharNumber());
        tpUser.setHoEmail(tpUser.getHoEmail());
        tpUser.setHoPr1(tpUser.getHoPr1());
        tpUser.setHoPr2(tpUser.getHoPr2());
        tpUser.setHoPr3(tpUser.getHoPr3());
        tpUser.setPcName(tpUser.getPcName());
        tpUser.setPcDesignation(tpUser.getPcDesignation());
        tpUser.setPcResAddress(tpUser.getPcResAddress());
        tpUser.setPcPermAddress(tpUser.getPcPermAddress());
        tpUser.setPcCitizen(tpUser.getPcCitizen());
        tpUser.setPcPhNumber(tpUser.getPcPhNumber());
        tpUser.setPcAltPhNumber(tpUser.getPcAltPhNumber());
        tpUser.setPcEmail(tpUser.getPcEmail());
        tpUser.setPcAltEmail(tpUser.getPcAltEmail());
        tpUser.setUserName(tpUser.getUserName());
        tpUser.setPassword(tpUser.getPassword());
        TpUser savedTpUser = this.tpUserRepo.save(tpUser);
        return savedTpUser;
    }

    @Override
    public TpUser getTpUserById(Integer tpUserId) {
        TpUser tpUser = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        return tpUser;
    }

    @Override
    public List<TpUser> getAllTpUser() {
        List<TpUser> users = this.tpUserRepo.findAll();
        return users;
    }

    @Override
    public void deleteTpUser(Integer tpUserId) {
        TpUser user = this.tpUserRepo.findById(tpUserId).orElseThrow(() -> new ResourceNotFoundException("TP", "Id", tpUserId));
        this.tpUserRepo.delete(user);
    }
}
