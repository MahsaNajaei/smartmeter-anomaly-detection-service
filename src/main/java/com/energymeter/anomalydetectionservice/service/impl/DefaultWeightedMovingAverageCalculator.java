package com.energymeter.anomalydetectionservice.service.impl;

import com.energymeter.anomalydetectionservice.exception.InSufficientDataForCalculationException;
import com.energymeter.anomalydetectionservice.service.WeightedMovingAverageCalculator;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@RequiredArgsConstructor
public class DefaultWeightedMovingAverageCalculator implements WeightedMovingAverageCalculator {

    private final static List<Double> weights = List.of(3d, 2d, 1d);

    @Override
    public double calculateWeightedAverageFor(List numbers) throws InSufficientDataForCalculationException {

        if (numbers.size() < weights.size())
            throw new InSufficientDataForCalculationException();

        var indexOfLatestInput = numbers.size();
        var weightedAverage = 0;

        for (var weight : weights)
            weightedAverage += weight * Double.parseDouble(numbers.get(--indexOfLatestInput).toString());


        return weightedAverage / weights.stream().reduce(0d, Double::sum);
    }

}
