package com.renanfch.delibird.core.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(final int id) {
        super(String.format("SchuduleId %d not found", id));
    }

}
