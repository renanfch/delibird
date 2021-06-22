package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import com.renanfch.delibird.core.validate.EmailValidate;
import com.renanfch.delibird.core.validate.PhoneNumberValidate;

import java.util.Arrays;
import java.util.function.Consumer;

public enum MessageServiceEnum {
    EMAIL(EmailValidate::validate),
    SMS(PhoneNumberValidate::validate),
    PUSH(EmailValidate::validate),
    WHATSAPP(PhoneNumberValidate::validate);

    private final Consumer<String> fun;

    MessageServiceEnum(final Consumer<String> fun) {
        this.fun = fun;
    }

    public void validate(final String recipient) {
        fun.accept(recipient);
    }

    public static MessageServiceEnum from(final String value) {
        return Arrays.stream(MessageServiceEnum.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new MessageServiceNotFoundException(value));
    }
}
