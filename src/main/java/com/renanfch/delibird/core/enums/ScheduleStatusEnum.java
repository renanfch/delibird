package com.renanfch.delibird.core.enums;

public enum ScheduleStatusEnum {
    SCHEDULED(0),
    SENT(1),
    CANCELED(2),
    ERROR(3);
    private final int code;

    ScheduleStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
