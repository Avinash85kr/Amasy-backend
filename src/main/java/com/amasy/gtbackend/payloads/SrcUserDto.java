package com.amasy.gtbackend.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SrcUserDto {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phNum;
    @NotEmpty
    private String oprState;
    @NotEmpty
    private SchCatDto schemeCat;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
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
