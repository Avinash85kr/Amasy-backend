package com.amasy.gtbackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int mprId;
    private String candidateName;
    private String fathersName;
    private String mothersName;
    private String dob;
    private String gender;
    private String religion;
    private String category;
    private String nationality;
    private String generalQualification;
    private String address;
    private String state;
    private String dist;
    private String city;
    private String pincode;
    private String mobile;
    private String email;
    private String sectorName;
    private String sectorCode;
    private String course;
    private String module;
    private String uid;
    private String trainingStartDate;
    private String trainingEndDate;
    private int trainingHours;
    private int ojtHours;
    private int totalHours;
    private int totalDays;
    private String prAbnNo;
    private String photo;
    private Date addedDate;
    private String status;
    private String projectId;
    @ManyToOne
    @JoinColumn(name = "centerId")
    private Center center;

}
