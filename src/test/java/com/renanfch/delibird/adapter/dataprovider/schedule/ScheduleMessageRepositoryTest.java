package com.renanfch.delibird.adapter.dataprovider.schedule;

import com.renanfch.delibird.adapter.dataprovider.entity.ScheduleMessageEntity;
import com.renanfch.delibird.adapter.dataprovider.mapper.ScheduleMessageMapper;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleMessageRepositoryTest {

    @Mock
    private ScheduleMessageEntityRepository repository;

    @InjectMocks
    private ScheduleMessageRepository scheduleMessageRepository;

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
                .status(ScheduleStatusEnum.SCHEDULED.toString())
                .messageService(MessageServiceEnum.EMAIL.toString())
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(scheduleMessageEntity));

        final var schedule = scheduleMessageRepository.findById(id);
        assertThat(schedule).isPresent();
        assertThat(schedule.get().getId()).isEqualTo(id);
        assertThat(schedule.get().getMessage()).isEqualTo(message);
        assertThat(schedule.get().getRecipient().getValue()).isEqualTo(email);
        verify(repository).findById(id);
    }

    @Test
    @DisplayName("Should save schedule create in repository")
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
                .status(ScheduleStatusEnum.SCHEDULED.toString())
                .messageService(MessageServiceEnum.EMAIL.toString())
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

    @Test
    @DisplayName("Should delele register in repository when cancel schedule")
    void shouldDeleleRegisterWhenCancelSchedule() {
        final var id = 1;

        scheduleMessageRepository.cancelSchedule(id);
        verify(repository).deleteById(id);
    }
}
