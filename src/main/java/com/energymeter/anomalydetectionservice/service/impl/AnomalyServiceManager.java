package com.energymeter.anomalydetectionservice.service.impl;

import com.energymeter.anomalydetectionservice.dto.ConsumptionStatistics;
import com.energymeter.anomalydetectionservice.entity.ConsumptionHistory;
import com.energymeter.anomalydetectionservice.exception.InSufficientDataForCalculationException;
import com.energymeter.anomalydetectionservice.repository.ConsumptionHistoryRepository;
import com.energymeter.anomalydetectionservice.service.AnomalyDetectionService;
import com.energymeter.anomalydetectionservice.service.WeightedMovingAverageCalculator;
import com.energymeter.anomalydetectionservice.service.client.NotificationServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnomalyServiceManager implements AnomalyDetectionService {

    private final WeightedMovingAverageCalculator weightedMovingAverageCalculator;
    private final ConsumptionHistoryRepository consumptionHistoryRepository;
    private final NotificationServiceClient notificationServiceClient;
    private final ModelMapper modelMapper;


    @Override
    public void inspectNewData(List<ConsumptionStatistics> consumptionStatisticsList) {
        for (ConsumptionStatistics cs : consumptionStatisticsList) {
            var previousConsumptions = consumptionHistoryRepository.findConsumptionByUserId(cs.getUserId());
            if (anomalyDetected(previousConsumptions, cs.getConsumption())) {
                notificationServiceClient.sendAnomalyNotificationToClient(cs.getUserId());
                log.warn("Anomaly is detected!");
            }

            ConsumptionHistory consumptionHistory = modelMapper.map(cs, ConsumptionHistory.class);
            consumptionHistoryRepository.save(consumptionHistory);
        }
    }

    private boolean anomalyDetected(List<Double> previousConsumptions, Double currentConsumption) {
        if (currentConsumption <= 0)
            return true;
        try {
            var preAverageConsumption = weightedMovingAverageCalculator.calculateWeightedAverageFor(previousConsumptions);
            return preAverageConsumption < currentConsumption;
        } catch (InSufficientDataForCalculationException e) {
            log.warn("Insufficient history for anomaly detection!");
        }
        return false;
    }


}
