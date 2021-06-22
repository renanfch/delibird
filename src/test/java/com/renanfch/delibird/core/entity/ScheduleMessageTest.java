package com.renanfch.delibird.core.entity;

import com.renanfch.delibird.core.vo.MessageService;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class ScheduleMessageTest {

    @Test
    @DisplayName("Should build ScheduleMessage when all parameter are valid")
    void shouldBuildScheduleMessageWhenParameterValid() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "message";
        final var id = 1;

        final var scheduleMessage = ScheduleMessage.builder()
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from(recipientValue, messageService))
                .message(message)
                .messageService(messageService)
                .scheduleStatus(ScheduleStatus.SCHEDULED)
                .id(id)
                .build();

        assertThat(scheduleMessage.getSendTime()).isEqualTo(LocalDateTime.MAX);
        assertThat(scheduleMessage.getRecipient().getValue()).isEqualTo(Recipient.from(recipientValue, messageService).getValue());
        assertThat(scheduleMessage.getMessage()).isEqualTo(message);
        assertThat(scheduleMessage.getMessageService()).isEqualTo(messageService);
        assertThat(scheduleMessage.getScheduleStatus()).isEqualTo(ScheduleStatus.SCHEDULED);
        assertThat(scheduleMessage.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when message is null")
    void shouldIllegalArgumentExceptionWhenMessageNull() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";

        final var scheduleMessage = ScheduleMessage.builder()
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from(recipientValue, messageService))
                .messageService(messageService);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(scheduleMessage::build);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when send time is null")
    void shouldIllegalArgumentExceptionWhenSendTimeNull() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "message";

        final var scheduleMessage = ScheduleMessage.builder()
                .message(message)
                .recipient(Recipient.from(recipientValue, messageService))
                .messageService(messageService);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(scheduleMessage::build);
    }

}
