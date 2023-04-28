package com.amasy.gtbackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CenterDto {
    private int id;
    @NotEmpty
    private String cenName;
    @NotEmpty
    private String cenAdd;
    @NotEmpty
    private String cenDist;
    @NotEmpty
    private String cenCity;
    @NotEmpty
    private String cenState;
    @NotEmpty
    private String cenPin;
    private String cenTel;
    @NotEmpty
    private String cenMob;
    @NotEmpty
    private String cenFax;
    @NotEmpty
    private String cenEmail;
    @NotEmpty
    private String ccpName;
    @NotEmpty
    private String ccpDesig;
    @NotEmpty
    private String ccpCitizen;
    @NotEmpty
    private String ccpDob;
    @NotEmpty
    private String ccpResAdd;
    @NotEmpty
    private String ccpPerAdd;
    @NotEmpty
    private String ccpMob1;
    @NotEmpty
    private String ccpMob2;
    @NotEmpty
    private String ccpEmail;
    @NotEmpty
    private String ccpQuali;
    @NotEmpty
    private String ccpExp;
    private String ccpPan;
    private String ccpAdhar;
    private String goAhead;
    private String cenStatus;
    private String snaStatus;
    private TpUserDto tpUser;
}
