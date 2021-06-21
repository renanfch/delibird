package com.renanfch.delibird.core.port;

import com.renanfch.delibird.core.entity.ScheduleMessage;

public interface NotifyScheduleAmqp {
    void notifyScheduleMessage(final ScheduleMessage scheduleMessage);
}
