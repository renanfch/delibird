package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.port.ScheduleRepository;
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
class SearchScheduleUseCaseTest {

    @Mock
    private ScheduleRepository repository;

    private SearchScheduleUseCase searchScheduleUseCase;

    @BeforeEach
    void setUp() {
        searchScheduleUseCase = spy(new SearchScheduleUseCase(repository));
    }

    @Nested
    class SearchScheduleById {

        @Test
        @DisplayName("Should return shedule message when find")
        void shouldReturnSheduleMessageWhenFind() {
            final var id = 1;
            final var scheduleMessage = mock(ScheduleMessage.class);

            when(repository.findById(id)).thenReturn(Optional.of(scheduleMessage));

            searchScheduleUseCase.getSheduleMessageById(id);
            verify(repository, times(1)).findById(id);
        }

        @Test
        @DisplayName("Should throw ScheduleNotFoundException when not found")
        void shouldThrowScheduleNotFoundExceptionWhenNotFound() {
            final var id = 1;

            when(repository.findById(id)).thenReturn(Optional.empty());

            assertThatExceptionOfType(ScheduleNotFoundException.class)
                    .isThrownBy(() -> searchScheduleUseCase.getSheduleMessageById(id))
                    .withMessage("SchuduleId 1 not found");
        }
    }

}
