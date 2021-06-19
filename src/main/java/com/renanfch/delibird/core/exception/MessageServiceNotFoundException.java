package com.renanfch.delibird.core.exception;

public class MessageServiceNotFoundException extends RuntimeException {

    public MessageServiceNotFoundException(final int id) {
        super(String.format("MessageServiceId %d not found", id));
    }

}
