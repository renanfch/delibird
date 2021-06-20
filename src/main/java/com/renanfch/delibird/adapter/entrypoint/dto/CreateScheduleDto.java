package com.renanfch.delibird.adapter.entrypoint.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class CreateScheduleDto {
    @NotNull
    private final LocalDateTime sendTime;
    @NotBlank
    private final String recipient;
    @NotBlank
    private final String messageService;
    @NotBlank
    private final String message;
}
