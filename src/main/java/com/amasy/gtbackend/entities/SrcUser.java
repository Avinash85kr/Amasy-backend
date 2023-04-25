package com.amasy.gtbackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SrcUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int srcId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phNum;
    @NotEmpty
    private String oprState;
    @ManyToOne
    @JoinColumn(name = "schemeId")
    private SchemeCat schemeCat;
    private String userName;
    private String password;
}
