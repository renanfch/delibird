package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.exception.ScheduleStateException;
import com.renanfch.delibird.core.port.ScheduleRepository;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveScheduleUseCaseTest {

    @Mock
    private ScheduleRepository repository;

    private RemoveScheduleUseCase removeScheduleUseCase;

    @BeforeEach
    void setUp() {
        removeScheduleUseCase = spy(new RemoveScheduleUseCase(repository));
    }

    @Nested
    class RemoveSchedule {

        @Test
        @DisplayName("Should remove when to cancel schedule")
        void shouldRemoveWhenToCancelSchedule() {
            final var id = 1;
            final var scheduleMessage = mock(ScheduleMessage.class);

            when(scheduleMessage.getScheduleStatusEnum()).thenReturn(ScheduleStatusEnum.SCHEDULED);
            when(repository.findById(id)).thenReturn(Optional.of(scheduleMessage));

            removeScheduleUseCase.cancel(id);
            verify(repository, times(1)).cancelSchedule(id);
            verify(repository, times(1)).findById(id);
        }

        @Test
        @DisplayName("Should throw ScheduleNotFound when schedule not found")
        void shouldThrowScheduleNotFoundWhenScheduleNotFound() {
            final var id = 1;

            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThatExceptionOfType(ScheduleNotFoundException.class)
                    .isThrownBy(() -> removeScheduleUseCase.cancel(id))
                    .withMessage("SchuduleId 1 not found");
        }

        @Test
        @DisplayName("Should throw ScheduleStateException when schedule is sent")
        void shouldThrowScheduleStateExceptionWhenScheduleIsSent() {
            final var id = 0;
            final var scheduleMessage = mock(ScheduleMessage.class);

            when(scheduleMessage.getScheduleStatusEnum()).thenReturn(ScheduleStatusEnum.SENT);
            when(repository.findById(id)).thenReturn(Optional.of(scheduleMessage));

            assertThatExceptionOfType(ScheduleStateException.class)
                    .isThrownBy(() -> removeScheduleUseCase.cancel(id))
                    .withMessage("Not possible remove schuduleId 0 with status SENT");
        }

    }

}
