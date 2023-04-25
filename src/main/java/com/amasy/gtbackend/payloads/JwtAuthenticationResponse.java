package com.amasy.gtbackend.payloads;

import com.amasy.gtbackend.entities.SrcUser;
import com.amasy.gtbackend.entities.TpUser;
import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private TpUser tpUser;
    private SrcUser srcUser;
}
