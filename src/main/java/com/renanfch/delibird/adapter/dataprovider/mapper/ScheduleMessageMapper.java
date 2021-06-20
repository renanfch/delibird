package com.renanfch.delibird.adapter.dataprovider.mapper;

import com.renanfch.delibird.adapter.dataprovider.entity.ScheduleMessageEntity;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.vo.Recipient;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;

public class ScheduleMessageMapper {
    private ScheduleMessageMapper() {
    }

    public static ScheduleMessageEntity toEntityDb(final CreateSchedule scheduleMessage){
        return ScheduleMessageEntity.builder()
                .sendTime(scheduleMessage.getSendTime())
                .recipient(scheduleMessage.getRecipient().getValue())
                .message(scheduleMessage.getMessage())
                .messageService(scheduleMessage.getMessageService())
                .scheduleStatusEnum(ScheduleStatusEnum.SCHEDULED)
                .build();

    }

    public static ScheduleMessage toScheduleMessage(final ScheduleMessageEntity scheduleMessageEntity) {
        final var recipient = Recipient.from(scheduleMessageEntity.getRecipient(), scheduleMessageEntity.getMessageService());

        return ScheduleMessage.builder()
                .id(scheduleMessageEntity.getId())
                .sendTime(scheduleMessageEntity.getSendTime())
                .recipient(recipient)
                .message(scheduleMessageEntity.getMessage())
                .messageService(scheduleMessageEntity.getMessageService())
                .scheduleStatusEnum(scheduleMessageEntity.getScheduleStatusEnum())
                .build();
    }
}
