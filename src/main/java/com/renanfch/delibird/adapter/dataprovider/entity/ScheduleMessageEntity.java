package com.renanfch.delibird.adapter.dataprovider.entity;

import com.renanfch.delibird.core.vo.MessageServiceEnum;
import com.renanfch.delibird.core.vo.ScheduleStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Table(name = "schedule_message")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScheduleMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime sendTime;
    private String recipient;
    private String message;
    @Enumerated(EnumType.STRING)
    private MessageServiceEnum messageService;
    @Enumerated(EnumType.STRING)
    private ScheduleStatusEnum scheduleStatusEnum;
}
