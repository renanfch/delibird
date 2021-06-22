package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.ScheduleStatusNotFoundException;

import java.util.Arrays;

public enum ScheduleStatusEnum {
    SCHEDULED,
    SENT,
    ERROR;

    public static ScheduleStatusEnum from(final String value) {
        return Arrays.stream(ScheduleStatusEnum.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ScheduleStatusNotFoundException(value));
    }

}
