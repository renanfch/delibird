package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import com.renanfch.delibird.core.validate.Email;
import com.renanfch.delibird.core.validate.PhoneNumber;

import java.util.Arrays;
import java.util.function.Consumer;

public enum MessageServiceEnum {
    EMAIL(Email::validate),
    SMS(PhoneNumber::validate),
    PUSH(Email::validate),
    WHATSAPP(PhoneNumber::validate);

    private final Consumer<String> fun;

    MessageServiceEnum(final Consumer<String> fun) {
        this.fun = fun;
    }

    public void validade(final String recipient) {
        fun.accept(recipient);
    }

    public static MessageServiceEnum from(final String value) {
        return Arrays.stream(MessageServiceEnum.values())
                .filter(it -> it.toString().equals(value))
                .findFirst()
                .orElseThrow(() -> new MessageServiceNotFoundException(value));
    }
}
