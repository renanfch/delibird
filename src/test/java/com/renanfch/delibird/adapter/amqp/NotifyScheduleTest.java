package com.renanfch.delibird.adapter.amqp;

import com.renanfch.delibird.core.entity.ScheduleMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.renanfch.delibird.adapter.amqp.NotifySchedule.ROUTING_KEY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotifyScheduleTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private NotifySchedule notifySchedule;

    @Test
    @DisplayName("Should send message to rabbit using rabbitTemplate")
    void shouldSendMessageToRabbit() {
        final var schedule = mock(ScheduleMessage.class);
        notifySchedule.notifyScheduleMessage(schedule);

        verify(rabbitTemplate).convertAndSend(ROUTING_KEY, schedule);
    }
}
