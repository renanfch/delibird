package com.renanfch.delibird.core.validate;

public class PhoneNumber {

    private PhoneNumber(){}

    public static void validate(final String value){
        if(value.length()<10 || value.length()>12)
            throw new IllegalArgumentException(String.format("PhoneNumber: %s format incorrect", value));
    }

}
