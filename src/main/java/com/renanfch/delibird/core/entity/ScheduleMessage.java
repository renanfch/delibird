package com.renanfch.delibird.core.entity;

import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
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
    private final MessageServiceEnum messageService;
    private final ScheduleStatusEnum scheduleStatusEnum;

    @Builder
    public ScheduleMessage(final int id, final LocalDateTime sendTime, final Recipient recipient,
                           final String message, final MessageServiceEnum messageService, final ScheduleStatusEnum scheduleStatusEnum) {
        this.id = id;
        this.sendTime = sendTime;
        this.recipient = recipient;
        this.message = message;
        this.messageService = messageService;
        this.scheduleStatusEnum = scheduleStatusEnum;

        Objects.requireNonNull(sendTime);
        Objects.requireNonNull(message);
    }

}
