package com.amasy.gtbackend.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BatchResponse {
    private int pendingBatches;
    private int centerId;
    private String centerName;
    private int ongoingBatches;
    private int completedBatches;
    private String projectId;
    private int totalBatches;
}
