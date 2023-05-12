package com.amasy.gtbackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BatchResponse {
    List<BatchDto> content;
    private int totalElements;
}
