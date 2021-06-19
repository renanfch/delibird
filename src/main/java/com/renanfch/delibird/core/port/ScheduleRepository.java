package com.renanfch.delibird.core.port;

import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.command.SearchSchedule;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ScheduleRepository {

    Optional<ScheduleMessage> findById(final int id);

    Page<ScheduleMessage> findByParams(final SearchSchedule searchSchedule);

    ScheduleMessage saveSchedule(final CreateSchedule createSchedule);

    void cancelSchedule(final int id);

}
