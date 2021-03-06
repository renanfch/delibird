package com.renanfch.delibird.core.command;

import com.renanfch.delibird.core.vo.MessageService;
import com.renanfch.delibird.core.vo.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.renanfch.delibird.core.command.CreateSchedule.CANNOT_CREATE_SCHEDULE_BEFORE_DATE;
import static com.renanfch.delibird.core.command.CreateSchedule.CANNOT_CREATE_SCHEDULE_WITHOUT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class CreateScheduleTest {

    @Test
    @DisplayName("Should build create schedule when all parameter are valid")
    void shouldBuildCreateScheduleWhenParameterValid() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "message";

        final var createSchedule = CreateSchedule.builder()
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from(recipientValue, messageService))
                .message(message)
                .messageService(messageService)
                .build();

        assertThat(createSchedule.getSendTime()).isEqualTo(LocalDateTime.MAX);
        assertThat(createSchedule.getRecipient().getValue()).isEqualTo(Recipient.from(recipientValue, messageService).getValue());
        assertThat(createSchedule.getMessage()).isEqualTo(message);
        assertThat(createSchedule.getMessageService()).isEqualTo(messageService);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when parameter date before now")
    void shouldIllegalArgumentExceptionWhenParameterInvalid() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "message";

        final var createSchedule = CreateSchedule.builder()
                .sendTime(LocalDateTime.MIN)
                .recipient(Recipient.from(recipientValue, messageService))
                .message(message)
                .messageService(messageService);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(createSchedule::build)
                .withMessage(CANNOT_CREATE_SCHEDULE_BEFORE_DATE);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when email is null")
    void shouldIllegalArgumentExceptionWhenMessageNull() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";

        final var createSchedule = CreateSchedule.builder()
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from(recipientValue, messageService))
                .messageService(messageService);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(createSchedule::build);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when send time is null")
    void shouldIllegalArgumentExceptionWhenSendTimeNull() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "message";

        final var createSchedule = CreateSchedule.builder()
                .recipient(Recipient.from(recipientValue, messageService))
                .message(message)
                .messageService(messageService);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(createSchedule::build);
    }

    @Test
    @DisplayName("Should IllegalArgumentException when message is empty")
    void shouldIllegalArgumentExceptionWhenMessageIsEmpty() {
        final var messageService = MessageService.EMAIL;
        final var recipientValue = "email@email.com";
        final var message = "";

        final var createSchedule = CreateSchedule.builder()
                .recipient(Recipient.from(recipientValue, messageService))
                .sendTime(LocalDateTime.MAX)
                .message(message)
                .messageService(messageService);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(createSchedule::build)
                .withMessage(CANNOT_CREATE_SCHEDULE_WITHOUT_MESSAGE);
    }

}
