package com.energymeter.anomalydetectionservice.service.client;

import com.energymeter.anomalydetectionservice.dto.NotificationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceClient {

    private final RestTemplate restTemplate;

    private final EurekaClient eurekaClient;

    private final String notifier = "ANOMALY-SERVICE";

    public void sendAnomalyNotificationToClient(Long userId) {
        var path = eurekaClient.getNextServerFromEureka("NOTIFICATION-SERVICE", false).getHomePageUrl() + "/notification";
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var notification = NotificationDTO.builder()
                .notifier(notifier)
                .receiptID(userId)
                .content("Anomaly detected in consumption!")
                .build();

        var requestEntity = new HttpEntity(notification, headers);
        var responseEntity = restTemplate.postForEntity(path, requestEntity, String.class);
        log.info("response from notification: " + responseEntity);
    }
}
