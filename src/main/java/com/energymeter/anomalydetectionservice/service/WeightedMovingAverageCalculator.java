package com.energymeter.anomalydetectionservice.service;

import com.energymeter.anomalydetectionservice.exception.InSufficientDataForCalculationException;

import java.util.List;

public interface WeightedMovingAverageCalculator {
    double calculateWeightedAverageFor(List numbers) throws InSufficientDataForCalculationException;
}
