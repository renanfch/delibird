package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.port.NotifyScheduleAmqp;
import com.renanfch.delibird.core.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleMessageUseCase {

    private final ScheduleRepository scheduleRepository;
    private final NotifyScheduleAmqp notifyScheduleAmqp;

    public ScheduleMessage create(final CreateSchedule createSchedule) {
        final var schedule = scheduleRepository.saveSchedule(createSchedule);
        notifyScheduleAmqp.notifyScheduleMessage(schedule);
        return schedule;
    }

}
