package com.amasy.gtbackend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TpUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    Scheme & Organisation detail

    @ManyToOne
    @JoinColumn(name = "scheme_id")
    private SchemeCat schemeCategory;
    private String orgName;
    @ManyToOne
    @JoinColumn(name = "org_cat_id")
    private OrgCat orgCategory;
    private String affiliation;

    // Registered office address
    private String roaAddress;
    private String roaDist;
    private String roaCity;
    private String roaState;
    private String roaPin;
    private String roaTelNo;
    private String roaMobNo;
    private String roaEmail;
    private String roaGst;

    // Regional or state office address
    private String soaAddress;
    private String soaDist;
    private String soaCity;
    private String soaState;
    private String soaPin;
    private String soaTelNo;
    private String soaMobNo;
    private String soaEmail;
    private String soaGst;
    private String website;
    private String panCard;
    private String panNumber;

//    Head owner detail

    private String hoName;
    private String hoQualification;
    private String hoDob;
    private String hoExp;
    private String hoCitizen;
    private String hoPanNumber;
    private String hoResAddress;
    private String hoPermAddress;
    private String hoPhNumber;
    private String hoAltPhNumber;
    private String hoAadharNumber;
    private String hoAltAadharNumber;
    private String hoEmail;
    private String hoPr1;
    private String hoPr2;
    private String hoPr3;

//    Project contact personal detail

    private String pcName;
    private String pcDesignation;
    private String pcResAddress;
    private String pcPermAddress;
    private String pcCitizen;
    private String pcPhNumber;
    private String pcAltPhNumber;
    private String pcEmail;
    private String pcAltEmail;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;
    private String status;
    @Column(name = "tmstmp")
    private Date addedDate;
    @OneToMany(mappedBy = "tpUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Center> centers = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tp_user_role", joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
