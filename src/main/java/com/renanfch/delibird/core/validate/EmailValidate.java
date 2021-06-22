package com.renanfch.delibird.core.validate;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class EmailValidate {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static void validate(final String value) {
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(value).find())
            throw new IllegalArgumentException(String.format("Email: %s format incorrect", value));
    }

}
