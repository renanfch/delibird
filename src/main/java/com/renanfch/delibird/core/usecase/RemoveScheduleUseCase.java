package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.enums.ScheduleStatusEnum;
import com.renanfch.delibird.core.exception.ScheduleStateException;
import com.renanfch.delibird.core.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveScheduleUseCase {

    private final ScheduleRepository scheduleRepository;
    private final SearchScheduleUseCase searchScheduleUseCase;

    public void cancel(int id) {
        final var schedule = searchScheduleUseCase.getById(id);
        if(schedule.getScheduleStatusEnum() == ScheduleStatusEnum.CANCELED || schedule.getScheduleStatusEnum() == ScheduleStatusEnum.SENT)
            throw new ScheduleStateException(schedule);

        scheduleRepository.cancelSchedule(id);
    }

}
