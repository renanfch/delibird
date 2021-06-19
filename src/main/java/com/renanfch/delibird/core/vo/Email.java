package com.renanfch.delibird.core.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Email implements Recipient {

    private final String email;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Email(final String value){
        if( !VALID_EMAIL_ADDRESS_REGEX.matcher(value).find() )
            throw new IllegalArgumentException(String.format("Email: %s format incorrect", value));

        this.email = value;
    }

    @Override
    public String getValue() {
        return null;
    }
}
