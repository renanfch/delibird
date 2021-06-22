package com.renanfch.delibird.core.validate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PhoneNumberValidate {

    static final String PHONE_FORMAT_INCORRECT = "PhoneNumber: %s format incorrect";

    public static void validate(final String value) {
        if (value.length() < 10 || value.length() > 12)
            throw new IllegalArgumentException(String.format(PHONE_FORMAT_INCORRECT, value));
    }

}
