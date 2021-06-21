package com.renanfch.delibird.adapter.dataprovider.schedule;

import com.renanfch.delibird.adapter.dataprovider.entity.ScheduleMessageEntity;
import com.renanfch.delibird.adapter.dataprovider.mapper.ScheduleMessageMapper;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleMessageRepositoryTest {

    @Mock
    private ScheduleMessageEntityRepository repository;

    private ScheduleMessageRepository scheduleMessageRepository;

    @BeforeEach
    void setUp() {
        scheduleMessageRepository = spy(new ScheduleMessageRepository(repository));
    }

    @Nested
    class findById {

        @Test
        @DisplayName("Should return scheduleMessage when search id")
        void shouldReturnScheduleMessageWhenSearchId() {
            final var id = 1;
            final var message = "message";
            final var email = "email@email.com";
            final var scheduleMessageEntity = ScheduleMessageEntity
                    .builder()
                    .message(message)
                    .recipient(email)
                    .id(id)
                    .sendTime(LocalDateTime.MAX)
                    .scheduleStatusEnum(ScheduleStatusEnum.SCHEDULED)
                    .messageService(MessageServiceEnum.EMAIL)
                    .build();

            when(repository.findById(id)).thenReturn(Optional.of(scheduleMessageEntity));

            final var schedule = scheduleMessageRepository.findById(id);
            assertThat(schedule).isPresent();
            assertThat(schedule.get().getId()).isEqualTo(id);
            assertThat(schedule.get().getMessage()).isEqualTo(message);
            assertThat(schedule.get().getRecipient().getValue()).isEqualTo(email);
            verify(repository, times(1)).findById(id);
        }

    }

    @Nested
    class saveSchedule {

        @Test
        @DisplayName("Should save schedule create")
        void shouldSaveScheduleCreate() {
            final var message = "message";
            final var email = "email@email.com";
            final var id = 1;

            final var scheduleMessageCreate = CreateSchedule
                    .builder()
                    .message(message)
                    .recipient(Recipient.from(email, MessageServiceEnum.EMAIL))
                    .sendTime(LocalDateTime.MAX)
                    .messageService(MessageServiceEnum.EMAIL)
                    .build();

            final var scheduleMessageEntity = ScheduleMessageEntity
                    .builder()
                    .id(id)
                    .message(message)
                    .recipient(email)
                    .sendTime(LocalDateTime.MAX)
                    .messageService(MessageServiceEnum.EMAIL)
                    .build();

            when(repository.save(ScheduleMessageMapper.toEntityDb(scheduleMessageCreate)))
                    .thenReturn(scheduleMessageEntity);

            final var schedule = scheduleMessageRepository.saveSchedule(scheduleMessageCreate);
            verify(repository).save(ScheduleMessageMapper.toEntityDb(scheduleMessageCreate));
            assertThat(schedule.getId()).isEqualTo(id);
            assertThat(schedule.getMessage()).isEqualTo(message);
            assertThat(schedule.getSendTime()).isEqualTo(LocalDateTime.MAX);
            assertThat(schedule.getMessageService()).isEqualTo(MessageServiceEnum.EMAIL);
        }
    }

    @Nested
    class cancelSchedule {

        @Test
        @DisplayName("Should delele register when cancel schedule")
        void shouldDeleleRegisterWhenCancelSchedule() {
            final var id = 1;

            doNothing().when(repository).deleteById(id);

            scheduleMessageRepository.cancelSchedule(id);
            verify(repository, times(1)).deleteById(id);
        }
    }
}
