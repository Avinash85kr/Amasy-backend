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
    private String cenId;
    private String centerPrId;
    private String centerId;
    private String prn;
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
    private String cenFax;
    @NotEmpty
    private String cenEmail;
    @NotEmpty
    private String ccpName;
    private String ccpDesig;
    private String ccpCitizen;
    private String ccpDob;
    private String ccpResAdd;
    private String ccpPerAdd;
    private String ccpMob1;
    private String ccpMob2;
    private String ccpEmail;
    private String ccpQuali;
    @NotEmpty
    private String ccpExp;
    private String ccpPan;
    private String ccpAdhar;
    private String goAhead;
    private String cenStatus;
    private String snaStatus;
    private TpUserDto tpUser;
    private CourseDto courseDto;
}
