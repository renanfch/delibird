package com.renanfch.delibird.adapter.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class CreateScheduleDto {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime sendTime;
    @NotBlank
    private final String recipient;
    @NotBlank
    private final String messageService;
    @NotBlank
    private final String message;
}
