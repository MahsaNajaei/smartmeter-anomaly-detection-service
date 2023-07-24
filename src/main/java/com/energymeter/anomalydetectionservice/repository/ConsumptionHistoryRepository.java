package com.energymeter.anomalydetectionservice.repository;

import com.energymeter.anomalydetectionservice.entity.ConsumptionHistory;

import java.util.List;

public interface ConsumptionHistoryRepository {

    List<Double> findConsumptionByUserId(long userId);

    ConsumptionHistory save(ConsumptionHistory consumptionHistory);
}
