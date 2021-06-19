package com.renanfch.delibird.core.enums;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;

import java.util.Arrays;

public enum MessageServiceEnum {
    EMAIL(0),
    SMS(1),
    PUSH(2),
    WHATSAPP(3);

    private final int code;

    MessageServiceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MessageServiceEnum fromCode(int code){
        return Arrays.stream(MessageServiceEnum.values())
                .filter(it->it.getCode() == code)
                .findFirst()
                .orElseThrow(()->new MessageServiceNotFoundException(code));
    }
}
