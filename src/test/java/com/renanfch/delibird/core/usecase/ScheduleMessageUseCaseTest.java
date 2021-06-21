package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.port.NotifyScheduleAmqp;
import com.renanfch.delibird.core.port.ScheduleRepository;
import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleMessageUseCaseTest {

    @Mock
    private ScheduleRepository repository;
    @Mock
    private NotifyScheduleAmqp notifyScheduleAmqp;

    private ScheduleMessageUseCase scheduleMessageUseCase;

    @BeforeEach
    void setUp() {
        scheduleMessageUseCase = spy(new ScheduleMessageUseCase(repository, notifyScheduleAmqp));
    }

    @Test
    @DisplayName("Should create schedule when save repository")
    void shouldCreateScheduleWhenSaveRepository() {
        final var createSchedule = CreateSchedule.builder()
                .message("message")
                .messageService(MessageServiceEnum.EMAIL)
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from("email@email.com", MessageServiceEnum.EMAIL))
                .build();

        final var sche = scheduleMessageUseCase.create(createSchedule);
        verify(repository, times(1)).saveSchedule(createSchedule);
        verify(notifyScheduleAmqp,times(1)).notifyScheduleMessage(sche);
    }
}
