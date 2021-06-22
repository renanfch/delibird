package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.ScheduleStatusNotFoundException;

import java.util.Arrays;

public enum ScheduleStatus {
    SCHEDULED,
    SENT,
    ERROR;

    public static ScheduleStatus from(final String value) {
        return Arrays.stream(ScheduleStatus.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ScheduleStatusNotFoundException(value));
    }

}
