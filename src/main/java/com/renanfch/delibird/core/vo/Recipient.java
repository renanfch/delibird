package com.renanfch.delibird.core.vo;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Recipient implements Serializable {
    private final String value;

    private Recipient(final String value) {
        this.value = value;
    }

    public static Recipient from(final String value, final MessageServiceEnum messageServiceEnum) {
        messageServiceEnum.validate(value);

        return new Recipient(value);
    }
}
