package com.renanfch.delibird.core.vo;

import lombok.Getter;

@Getter
public class Recipient {
    private final String value;

    private Recipient(final String value) {
        this.value = value;
    }

    public static Recipient from(final String value, final MessageServiceEnum messageServiceEnum) {
        messageServiceEnum.validade(value);

        return new Recipient(value);
    }
}
