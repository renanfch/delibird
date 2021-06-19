package com.renanfch.delibird.adapter.entrypoint.mapper;

import com.renanfch.delibird.adapter.entrypoint.dto.SearchScheduleDto;
import com.renanfch.delibird.core.command.SearchSchedule;
import com.renanfch.delibird.core.enums.MessageServiceEnum;

public class SearchScheduleMapper {

    private SearchScheduleMapper(){}

    public static SearchSchedule toEntity(final SearchScheduleDto searchScheduleDto) {
        return SearchSchedule.builder()
                .scheduleStart(searchScheduleDto.getScheduleStart())
                .scheduleEnd(searchScheduleDto.getScheduleEnd())
                .messageService(MessageServiceEnum.fromCode(searchScheduleDto.getMessageService()))
                .page(searchScheduleDto.getPage())
                .size(searchScheduleDto.getSize())
                .build();
    }

}
