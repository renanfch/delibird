package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import com.renanfch.delibird.core.validate.EmailValidate;
import com.renanfch.delibird.core.validate.PhoneNumberValidate;

import java.util.Arrays;
import java.util.function.Consumer;

public enum MessageService {
    EMAIL(EmailValidate::validate),
    SMS(PhoneNumberValidate::validate),
    PUSH(EmailValidate::validate),
    WHATSAPP(PhoneNumberValidate::validate);

    private final Consumer<String> fun;

    MessageService(final Consumer<String> fun) {
        this.fun = fun;
    }

    public void validate(final String recipient) {
        fun.accept(recipient);
    }

    public static MessageService from(final String value) {
        return Arrays.stream(MessageService.values())
                .filter(it -> it.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new MessageServiceNotFoundException(value));
    }
}
