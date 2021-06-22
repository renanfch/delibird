package com.renanfch.delibird.adapter.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScheduleResponseDto {
    private final int id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime sendTime;
    private final String recipient;
    private final String messageService;
    private final String message;
    private final String scheduleStatusEnum;
}
