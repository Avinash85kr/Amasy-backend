package com.amasy.gtbackend.payloads;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private TpUserDto tpUser;
    private SrcUserDto srcUser;
}
