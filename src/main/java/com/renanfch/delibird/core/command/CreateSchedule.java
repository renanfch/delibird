package com.renanfch.delibird.core.command;

import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class CreateSchedule {
    private final LocalDateTime sendTime;
    private final Recipient recipient;
    private final String message;
    private final MessageServiceEnum messageService;

    @Builder
    public CreateSchedule(final LocalDateTime sendTime, final Recipient recipient, final String message, final MessageServiceEnum messageService) {
        this.sendTime = sendTime;
        this.recipient = recipient;
        this.message = message;
        this.messageService = messageService;

        Objects.requireNonNull(sendTime);
        if(sendTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Cannot create schedule before current date!");

        Objects.requireNonNull(message);
    }
}
