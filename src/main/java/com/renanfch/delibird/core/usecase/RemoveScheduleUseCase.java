package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
import com.renanfch.delibird.core.exception.ScheduleStateException;
import com.renanfch.delibird.core.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveScheduleUseCase {

    private final ScheduleRepository scheduleRepository;

    public void cancel(final int id) {
        final var schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        if (schedule.getScheduleStatusEnum() == ScheduleStatusEnum.SENT)
            throw new ScheduleStateException(schedule);

        scheduleRepository.cancelSchedule(id);
    }

}
