package com.renanfch.delibird.adapter.entrypoint.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScheduleResponseDto {
    private final LocalDateTime sendTime;
    private final String recipient;
    private final int messageService;
    private final String message;
}
