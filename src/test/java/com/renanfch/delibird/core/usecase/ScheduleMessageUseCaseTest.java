package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.port.ScheduleNotifier;
import com.renanfch.delibird.core.port.ScheduleRepository;
import com.renanfch.delibird.core.vo.MessageService;
import com.renanfch.delibird.core.vo.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleMessageUseCaseTest {

    @Mock
    private ScheduleRepository repository;
    @Mock
    private ScheduleNotifier scheduleNotifier;

    @InjectMocks
    private ScheduleMessageUseCase scheduleMessageUseCase;

    @Test
    @DisplayName("Should create schedule when call save repository")
    void shouldCreateScheduleWhenSaveRepository() {
        final var message = "message";
        final var scheduleMessage = mock(ScheduleMessage.class);

        final var createSchedule = CreateSchedule.builder()
                .message(message)
                .messageService(MessageService.EMAIL)
                .sendTime(LocalDateTime.MAX)
                .recipient(Recipient.from("email@email.com", MessageService.EMAIL))
                .build();

        when(scheduleMessage.getMessage()).thenReturn(message);
        when(repository.saveSchedule(createSchedule)).thenReturn(scheduleMessage);

        final var schedule = scheduleMessageUseCase.create(createSchedule);
        verify(repository).saveSchedule(createSchedule);
        verify(scheduleNotifier).notifyScheduleMessage(schedule);
        assertThat(schedule.getMessage()).isEqualTo(message);
    }
}
