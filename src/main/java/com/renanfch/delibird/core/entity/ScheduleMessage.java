package com.renanfch.delibird.core.entity;

import com.renanfch.delibird.core.vo.MessageService;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatus;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class ScheduleMessage implements Serializable {

    private final int id;
    private final LocalDateTime sendTime;
    private final Recipient recipient;
    private final String message;
    private final MessageService messageService;
    private final ScheduleStatus scheduleStatus;

    @Builder
    public ScheduleMessage(final int id, final LocalDateTime sendTime, final Recipient recipient,
                           final String message, final MessageService messageService, final ScheduleStatus scheduleStatus) {
        this.id = id;
        this.sendTime = sendTime;
        this.recipient = recipient;
        this.message = message;
        this.messageService = messageService;
        this.scheduleStatus = scheduleStatus;

        Objects.requireNonNull(sendTime);
        Objects.requireNonNull(message);
    }

}
