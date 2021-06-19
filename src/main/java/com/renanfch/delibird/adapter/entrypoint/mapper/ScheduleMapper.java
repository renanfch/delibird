package com.renanfch.delibird.adapter.entrypoint.mapper;

import com.renanfch.delibird.adapter.entrypoint.dto.CreateScheduleDto;
import com.renanfch.delibird.adapter.entrypoint.dto.ScheduleResponseDto;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.enums.MessageServiceEnum;
import com.renanfch.delibird.core.vo.Email;
import com.renanfch.delibird.core.vo.PhoneNumber;
import com.renanfch.delibird.core.vo.Recipient;

public class ScheduleMapper {
    private ScheduleMapper(){}

    public static CreateSchedule toEntity(final CreateScheduleDto scheduleRegisterDto){
        final var msgService = MessageServiceEnum.fromCode(scheduleRegisterDto.getMessageService());

        Recipient recipient;
        if(msgService==MessageServiceEnum.EMAIL)
            recipient = new Email(scheduleRegisterDto.getRecipient());
        else
            recipient = new PhoneNumber(scheduleRegisterDto.getRecipient());

        return CreateSchedule.builder()
                .sendTime(scheduleRegisterDto.getSendTime())
                .recipient(recipient)
                .messageService(msgService)
                .message(scheduleRegisterDto.getMessage())
                .build();
    }

    public static ScheduleResponseDto toDtoResponse(final ScheduleMessage scheduleMessage) {
        return ScheduleResponseDto.builder()
                .sendTime(scheduleMessage.getSendTime())
                .recipient(scheduleMessage.getRecipient().getValue())
                .messageService(scheduleMessage.getMessageService().getCode())
                .message(scheduleMessage.getMessage())
                .build();
    }
}
