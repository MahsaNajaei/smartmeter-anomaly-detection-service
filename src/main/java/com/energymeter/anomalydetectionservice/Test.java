package com.energymeter.anomalydetectionservice;

import com.energymeter.anomalydetectionservice.dto.ConsumptionStatistics;
import com.energymeter.anomalydetectionservice.service.AnomalyDetectionService;
import com.energymeter.anomalydetectionservice.service.client.NotificationServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Test {
    public final AnomalyDetectionService anomalyDetectionService;
    private final NotificationServiceClient notificationServiceClient;

    @GetMapping("/test")
    public void test(){
        List<ConsumptionStatistics> consumptionStatisticsList = new ArrayList<>();
        ConsumptionStatistics.ConsumptionStatisticsBuilder c = ConsumptionStatistics.builder().consumption(2003d).userId(1l);
        consumptionStatisticsList.add(c.build());
        c.consumption(7000d);
        consumptionStatisticsList.add(c.build());
        c.consumption(1000d).userId(2l);
        consumptionStatisticsList.add(c.build());
        anomalyDetectionService.inspectNewData(consumptionStatisticsList);
    }
}
