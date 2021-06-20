package com.renanfch.delibird.adapter.dataprovider.schedule;

import com.renanfch.delibird.adapter.dataprovider.mapper.ScheduleMessageMapper;
import com.renanfch.delibird.core.command.CreateSchedule;
import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.port.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleMessageRepository implements ScheduleRepository {

    private final ScheduleMessageEntityRepository scheduleMessageEntityRepository;

    @Override
    public Optional<ScheduleMessage> findById(final int id) {
        return scheduleMessageEntityRepository.findById(id)
                .map(ScheduleMessageMapper::toScheduleMessage);
    }

    @Override
    public ScheduleMessage saveSchedule(final CreateSchedule createSchedule) {
        final var scheduleEntity = scheduleMessageEntityRepository.save(ScheduleMessageMapper.toEntityDb(createSchedule));
        return ScheduleMessageMapper.toScheduleMessage(scheduleEntity);
    }

    @Override
    public void cancelSchedule(final int id) {
        scheduleMessageEntityRepository.deleteById(id);
    }
}
