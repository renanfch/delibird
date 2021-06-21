package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class MessageServiceEnumTest {

    @Nested
    class from {

        @Test
        @DisplayName("Should return EmailEnum when value EMAIL parameter")
        void shouldReturnEmailEnumWhenValueEmail() {
            final var messageService = MessageServiceEnum.from("EMAIL");
            assertThat(messageService).isEqualTo(MessageServiceEnum.EMAIL);
        }

        @Test
        @DisplayName("Should return SmsEnum when value SMS parameter")
        void shouldReturnSmsEnumWhenValueSms() {
            final var messageService = MessageServiceEnum.from("SMS");
            assertThat(messageService).isEqualTo(MessageServiceEnum.SMS);
        }

        @Test
        @DisplayName("Should return PushEnum when value PUSH parameter")
        void shouldReturnPushEnumWhenValuePush() {
            final var messageService = MessageServiceEnum.from("PUSH");
            assertThat(messageService).isEqualTo(MessageServiceEnum.PUSH);
        }

        @Test
        @DisplayName("Should return WhatsAppEnum when value WHATSAPP parameter")
        void shouldReturnWhatsAppEnumWhenValueWhatsApp() {
            final var messageService = MessageServiceEnum.from("WHATSAPP");
            assertThat(messageService).isEqualTo(MessageServiceEnum.WHATSAPP);
        }

        @Test
        @DisplayName("Should throw ThrowMessageServiceNotFoundException when value abc parameter")
        void shouldThrowMessageServiceNotFoundExceptionWhenNotFoundParameter() {
            assertThatExceptionOfType(MessageServiceNotFoundException.class)
                    .isThrownBy(() -> MessageServiceEnum.from("abc"))
                    .withMessage("MessageService abc not found");
        }

    }


}
