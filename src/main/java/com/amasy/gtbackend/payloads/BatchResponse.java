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
    private int pending;
    private int centerId;
    private String centerName;
    private int ongoingBatches;
    private int completedBatches;
    private String projectId;
    private int totalElements;
}
