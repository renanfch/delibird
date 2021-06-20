package com.renanfch.delibird.core.port;

import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;

import java.util.Optional;

public interface ScheduleRepository {

    Optional<ScheduleMessage> findById(final int id);

    ScheduleMessage saveSchedule(final CreateSchedule createSchedule);

    void cancelSchedule(final int id);

}
