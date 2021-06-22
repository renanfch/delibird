package com.renanfch.delibird.core.port;

import com.renanfch.delibird.core.entity.ScheduleMessage;

public interface ScheduleNotifier {
    void notifyScheduleMessage(final ScheduleMessage scheduleMessage);
}
