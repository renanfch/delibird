package com.renanfch.delibird.core.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class RecipientTest {

    @Test
    @DisplayName("Should return email Value when email is valid")
    void shouldReturnEmailValueWhenEmailIsValid() {
        final var email = "email@email.com";

        final var recipient = Recipient.from(email, MessageServiceEnum.EMAIL);
        assertThat(recipient.getValue()).isEqualTo(email);
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when email invalid")
    void shouldThrowIllegalArgumentExceptionWhenEmailInvalid() {
        final var email = "email";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Recipient.from(email, MessageServiceEnum.EMAIL))
                .withMessage("Email: email format incorrect");
    }

    @Test
    @DisplayName("Should return phone number value when phone number is valid to sms")
    void shouldReturnPhoneNumberValueWhenPhoneNumberIsValidToSms() {
        final var phone = "1699887766";

        final var recipient = Recipient.from(phone, MessageServiceEnum.SMS);
        assertThat(recipient.getValue()).isEqualTo(phone);
    }

    @ParameterizedTest
    @CsvSource({"123456789", "1234567891234"})
    @DisplayName("Should throw IllegalArgumentException when phone number invalid")
    void shouldThrowIllegalArgumentExceptionWhenPhoneNumberInvalid(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Recipient.from(value, MessageServiceEnum.SMS))
                .withMessage("PhoneNumber: " + value + " format incorrect");
    }

    @ParameterizedTest
    @CsvSource({"1699887766", "16998877661", "169988776612"})
    @DisplayName("Should return phone number value when phone number is valid to whatsapp")
    void shouldReturnPhoneNumberValueWhenPhoneNumberIsValidToWhatsApp(String value) {
        final var recipient = Recipient.from(value, MessageServiceEnum.WHATSAPP);
        assertThat(recipient.getValue()).isEqualTo(value);
    }

}
