package com.renanfch.delibird.core.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PhoneNumber implements Recipient{
    private final String phone;

    public PhoneNumber(String value){
        if(value.length()<10 || value.length()>12)
            throw new IllegalArgumentException(String.format("PhoneNumber: %s format incorrect", value));

        this.phone = value;
    }

    @Override
    public String getValue() {
        return phone;
    }
}
