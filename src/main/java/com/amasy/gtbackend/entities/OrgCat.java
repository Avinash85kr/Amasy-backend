package com.amasy.gtbackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrgCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgId;
    @Column(name = "orgCatName")
    private String orgType;
    @OneToMany(mappedBy = "orgCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TpUser> tpUsers = new ArrayList<>();
}
