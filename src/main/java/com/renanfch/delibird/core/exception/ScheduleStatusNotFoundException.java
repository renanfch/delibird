package com.renanfch.delibird.core.exception;

public class ScheduleStatusNotFoundException extends RuntimeException {

    public ScheduleStatusNotFoundException(final String value) {
        super(String.format("ScheduleStatus %s not found", value));
    }

}
