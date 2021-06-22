package com.renanfch.delibird.adapter.dataprovider.mapper;

import com.renanfch.delibird.adapter.dataprovider.entity.ScheduleMessageEntity;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.vo.MessageService;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatus;

public class ScheduleMessageMapper {
    private ScheduleMessageMapper() {
    }

    public static ScheduleMessageEntity toEntityDb(final CreateSchedule scheduleMessage) {
        return ScheduleMessageEntity.builder()
                .sendTime(scheduleMessage.getSendTime())
                .recipient(scheduleMessage.getRecipient().getValue())
                .message(scheduleMessage.getMessage())
                .messageService(scheduleMessage.getMessageService().toString())
                .status(ScheduleStatus.SCHEDULED.toString())
                .build();

    }

    public static ScheduleMessage toScheduleMessage(final ScheduleMessageEntity scheduleMessageEntity) {
        final var messageService = MessageService.from(scheduleMessageEntity.getMessageService());
        final var recipient = Recipient.from(scheduleMessageEntity.getRecipient(), messageService);

        return ScheduleMessage.builder()
                .id(scheduleMessageEntity.getId())
                .sendTime(scheduleMessageEntity.getSendTime())
                .recipient(recipient)
                .message(scheduleMessageEntity.getMessage())
                .messageService(messageService)
                .scheduleStatus(ScheduleStatus.from(scheduleMessageEntity.getStatus()))
                .build();
    }
}
