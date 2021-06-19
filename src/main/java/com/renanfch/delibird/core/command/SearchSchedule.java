package com.renanfch.delibird.core.command;

import com.renanfch.delibird.core.enums.MessageServiceEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class SearchSchedule {
    private final LocalDateTime scheduleStart;
    private final LocalDateTime scheduleEnd;
    private final MessageServiceEnum messageService;
    private final int page;
    @Max(value=50)
    private final int size;
}
