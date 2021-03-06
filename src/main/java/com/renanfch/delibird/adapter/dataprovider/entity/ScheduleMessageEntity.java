package com.renanfch.delibird.adapter.dataprovider.entity;

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
    private String messageService;
    private String status;
}
