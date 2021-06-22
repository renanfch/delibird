package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class MessageServiceTest {

    @Test
    @DisplayName("Should return EmailEnum when value EMAIL parameter")
    void shouldReturnEmailEnumWhenValueEmail() {
        final var messageService = MessageService.from("EMAIL");
        assertThat(messageService).isEqualTo(MessageService.EMAIL);
    }

    @Test
    @DisplayName("Should return SmsEnum when value SMS parameter")
    void shouldReturnSmsEnumWhenValueSms() {
        final var messageService = MessageService.from("SMS");
        assertThat(messageService).isEqualTo(MessageService.SMS);
    }

    @Test
    @DisplayName("Should return PushEnum when value PUSH parameter")
    void shouldReturnPushEnumWhenValuePush() {
        final var messageService = MessageService.from("PUSH");
        assertThat(messageService).isEqualTo(MessageService.PUSH);
    }

    @Test
    @DisplayName("Should return WhatsAppEnum when value WHATSAPP parameter")
    void shouldReturnWhatsAppEnumWhenValueWhatsApp() {
        final var messageService = MessageService.from("WHATSAPP");
        assertThat(messageService).isEqualTo(MessageService.WHATSAPP);
    }

    @Test
    @DisplayName("Should throw ThrowMessageServiceNotFoundException when value abc parameter")
    void shouldThrowMessageServiceNotFoundExceptionWhenNotFoundParameter() {
        assertThatExceptionOfType(MessageServiceNotFoundException.class)
                .isThrownBy(() -> MessageService.from("abc"))
                .withMessage("MessageService abc not found");
    }

}
