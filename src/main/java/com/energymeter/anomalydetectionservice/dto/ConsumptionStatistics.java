package com.energymeter.anomalydetectionservice.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ConsumptionStatistics {
    private Long energyMeterId;
    private Long userId;
    private Double consumption;
    private Timestamp calculatedAt;
}
