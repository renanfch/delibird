package com.renanfch.delibird.adapter.entrypoint.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(1)
    @Max(4)
    private final int messageService;
    @NotBlank
    private final String message;
}
