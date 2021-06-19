package com.renanfch.delibird.adapter.entrypoint.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Getter
@Builder
public class SearchScheduleDto {
    private final LocalDateTime scheduleStart;
    private final LocalDateTime scheduleEnd;
    private final String recipient;
    private final int messageService;
    private final String message;
    private final int page;
    @Max(value=50)
    private final int size;
}
