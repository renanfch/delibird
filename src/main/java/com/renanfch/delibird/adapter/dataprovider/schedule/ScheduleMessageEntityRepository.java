package com.renanfch.delibird.adapter.dataprovider.schedule;

import com.renanfch.delibird.adapter.dataprovider.entity.ScheduleMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleMessageEntityRepository extends JpaRepository<ScheduleMessageEntity, Integer> {
}
