package com.renanfch.delibird.core.command;

import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class CreateSchedule {
    private final LocalDateTime sendTime;
    private final Recipient recipient;
    private final String message;
    private final MessageServiceEnum messageService;

    static final String CANNOT_CREATE_SCHEDULE_BEFORE_DATE = "Cannot create schedule before current date!";
    static final String CANNOT_CREATE_SCHEDULE_WITHOUT_MESSAGE = "Cannot create schedule without message!";

    @Builder
    public CreateSchedule(final LocalDateTime sendTime, final Recipient recipient, final String message, final MessageServiceEnum messageService) {
        this.sendTime = sendTime;
        this.recipient = recipient;
        this.message = message;
        this.messageService = messageService;

        Objects.requireNonNull(sendTime);
        if (sendTime.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException(CANNOT_CREATE_SCHEDULE_BEFORE_DATE);

        Objects.requireNonNull(message);
        if (message.length() <= 0)
            throw new IllegalArgumentException(CANNOT_CREATE_SCHEDULE_WITHOUT_MESSAGE);

    }
}
