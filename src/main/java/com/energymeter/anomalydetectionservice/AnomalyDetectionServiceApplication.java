package com.energymeter.anomalydetectionservice;

import com.energymeter.anomalydetectionservice.service.WeightedMovingAverageCalculator;
import com.energymeter.anomalydetectionservice.service.impl.DefaultWeightedMovingAverageCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AnomalyDetectionServiceApplication {

    private static WeightedMovingAverageCalculator calculator = new DefaultWeightedMovingAverageCalculator();

    public static void main(String[] args) {
        SpringApplication.run(AnomalyDetectionServiceApplication.class, args);
    }

}
