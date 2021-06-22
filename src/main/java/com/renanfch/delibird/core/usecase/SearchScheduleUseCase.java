package com.renanfch.delibird.core.usecase;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchScheduleUseCase {

    private final ScheduleRepository scheduleRepository;

    public ScheduleMessage getScheduleMessageById(final int id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }

}
