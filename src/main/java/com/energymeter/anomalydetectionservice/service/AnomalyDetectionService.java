package com.energymeter.anomalydetectionservice.service;

import com.energymeter.anomalydetectionservice.dto.ConsumptionStatistics;

import java.util.List;

public interface AnomalyDetectionService {
    void inspectNewData(List<ConsumptionStatistics> consumptionStatisticsList);
}
