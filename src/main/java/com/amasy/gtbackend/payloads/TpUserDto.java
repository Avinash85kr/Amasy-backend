package com.amasy.gtbackend.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TpUserDto {
    private int id;
    private SchCatDto schemeCategory;
    @NotBlank
    private String orgName;
    private OrgCatDto orgCategory;
    @NotBlank
    private String affiliation;
    @NotEmpty
    private String roaAddress;
    @NotEmpty
    private String roaDist;
    @NotEmpty
    private String roaCity;
    @NotEmpty
    private String roaState;
    @NotEmpty
    private String roaPin;
    @NotEmpty
    private String roaTelNo;
    @NotEmpty
    private String roaMobNo;
    @NotEmpty
    private String roaEmail;
    @NotEmpty
    private String roaGst;
    @NotEmpty
    private String soaAddress;
    @NotEmpty
    private String soaDist;
    @NotEmpty
    private String soaCity;
    @NotEmpty
    private String soaState;
    @NotEmpty
    private String soaPin;
    @NotEmpty
    private String soaTelNo;
    @NotEmpty
    private String soaMobNo;
    @NotEmpty
    private String soaEmail;
    @NotEmpty
    private String soaGst;
    @NotBlank
    private String website;
    @NotEmpty
    private String panCard;
    @NotEmpty
    private String panNumber;
    @NotEmpty
    private String hoName;
    @NotEmpty
    private String hoQualification;
    @NotEmpty
    private String hoDob;
    @NotEmpty
    private String hoExp;
    @NotEmpty
    private String hoCitizen;
    @NotEmpty
    private String hoPanNumber;
    @NotEmpty
    private String hoResAddress;
    @NotEmpty
    private String hoPermAddress;
    @NotEmpty
    private String hoPhNumber;
    private String hoAltPhNumber;
    @NotEmpty
    private String hoAadharNumber;
    private String hoAltAadharNumber;
    @NotEmpty
    private String hoEmail;
    private String hoPr1;
    private String hoPr2;
    private String hoPr3;
    @NotEmpty
    private String pcName;
    @NotEmpty
    private String pcDesignation;
    @NotEmpty
    private String pcResAddress;
    @NotEmpty
    private String pcPermAddress;
    @NotEmpty
    private String pcCitizen;
    @NotEmpty
    private String pcPhNumber;
    private String pcAltPhNumber;
    @NotEmpty
    @Email
    private String pcEmail;
    @NotEmpty
    @Email
    private String pcAltEmail;
    @NotEmpty
    private String userName;
    private String password;
    private String status;
    private Date addedDate;
    private Set<RoleDto> roles = new HashSet<>();

//    @JsonIgnore
//    public String getPassword(){
//        return  this.password;
//    }
//    @JsonProperty
//    public void setPassword(String password) {
//        this.password = password;
//    }

}
