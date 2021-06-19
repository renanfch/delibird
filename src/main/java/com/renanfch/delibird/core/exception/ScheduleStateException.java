package com.renanfch.delibird.core.exception;

import com.renanfch.delibird.core.entity.ScheduleMessage;

public class ScheduleStateException extends RuntimeException {
    public ScheduleStateException(final ScheduleMessage schedule) {
        super(String.format("Not possible remove schuduleId %d with status %s",
                schedule.getId(),
                schedule.getScheduleStatusEnum().toString()));
    }
}
