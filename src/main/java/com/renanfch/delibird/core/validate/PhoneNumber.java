package com.renanfch.delibird.core.validate;

public class PhoneNumber {

    static final String PHONE_FORMAT_INCORRECT = "PhoneNumber: %s format incorrect";

    private PhoneNumber() {
    }

    public static void validate(final String value) {
        if (value.length() < 10 || value.length() > 12)
            throw new IllegalArgumentException(String.format(PHONE_FORMAT_INCORRECT, value));
    }

}
