package com.energymeter.anomalydetectionservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;

@Data
public class ConsumptionHistory {
    @Id
    @Transient
    private long id;
    private long userId;
    private double consumption;
    private Timestamp calculatedAt;
}
