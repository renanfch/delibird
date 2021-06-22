package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.port.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchScheduleUseCaseTest {

    @Mock
    private ScheduleRepository repository;

    @InjectMocks
    private SearchScheduleUseCase searchScheduleUseCase;

    @Test
    @DisplayName("Should return shedule message when find in repository")
    void shouldReturnSheduleMessageWhenFind() {
        final var id = 1;
        final var scheduleMessage = mock(ScheduleMessage.class);

        when(scheduleMessage.getId()).thenReturn(id);
        when(repository.findById(id)).thenReturn(Optional.of(scheduleMessage));

        final var schedule = searchScheduleUseCase.getScheduleMessageById(id);
        verify(repository).findById(id);
        assertThat(schedule.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should throw ScheduleNotFoundException when not found in repository")
    void shouldThrowScheduleNotFoundExceptionWhenNotFound() {
        final var id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThatExceptionOfType(ScheduleNotFoundException.class)
                .isThrownBy(() -> searchScheduleUseCase.getScheduleMessageById(id))
                .withMessage("SchuduleId 1 not found");
    }

}
