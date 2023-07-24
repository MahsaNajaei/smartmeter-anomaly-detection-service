package com.energymeter.anomalydetectionservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationDTO {
    private String notifier;
    private long receiptID;
    private String content;
}
