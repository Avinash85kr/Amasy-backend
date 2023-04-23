package com.amasy.gtbackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@NoArgsConstructor
public class OrgCatDto {
    private Integer orgId;
    @NotBlank
    private String orgType;
}
