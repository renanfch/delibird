package com.renanfch.delibird.adapter.amqp;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import com.renanfch.delibird.core.port.NotifyScheduleAmqp;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotifySchedule implements NotifyScheduleAmqp {

    private final RabbitTemplate rabbitTemplate;

    static final String ROUTING_KEY = "renanfch.schedule.*";

    @Override
    public void notifyScheduleMessage(final ScheduleMessage scheduleMessage) {
        rabbitTemplate.convertAndSend(ROUTING_KEY, scheduleMessage);
    }

}
