package com.renanfch.delibird.core.command;

import com.renanfch.delibird.core.enums.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class CreateSchedule {
    private final LocalDateTime sendTime;
    private final Recipient recipient;
    private final String message;
    private final MessageServiceEnum messageService;
}
