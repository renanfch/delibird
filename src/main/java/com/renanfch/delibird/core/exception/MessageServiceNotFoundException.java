package com.renanfch.delibird.core.exception;

public class MessageServiceNotFoundException extends RuntimeException {

    public MessageServiceNotFoundException(final String value) {
        super(String.format("MessageService %s not found", value));
    }

}
