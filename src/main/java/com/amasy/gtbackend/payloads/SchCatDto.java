package com.amasy.gtbackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SchCatDto {
    private Integer schemeId;
    @NotBlank
    private String schemeName;
}
