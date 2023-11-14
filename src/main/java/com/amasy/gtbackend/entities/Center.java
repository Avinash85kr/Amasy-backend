package com.amasy.gtbackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cenId;
    private String centerPrId;
    private String centerId;
    private String prn;
    private String cenName;
    private String cenAdd;
    private String cenDist;
    private String cenCity;
    private String cenState;
    private String cenPin;
    private String cenTel;
    private String cenMob;
    private String cenFax;
    private String cenEmail;
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
    private String ccpExp;
    private String ccpPan;
    private String ccpAdhar;
    private String goAhead;
    private String cenStatus;
    private String snaStatus;
    @ManyToOne
    @JoinColumn(name = "tp_user_id")
    private TpUser tpUser;
    @ManyToMany
    @JoinColumn(name = "courseId")
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Batch> batches = new HashSet<>();
}
