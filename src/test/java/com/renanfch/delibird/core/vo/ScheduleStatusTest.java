package com.renanfch.delibird.core.vo;

import com.renanfch.delibird.core.exception.ScheduleStatusNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class ScheduleStatusTest {
    @Test
    @DisplayName("Should return scheduledEnum when from scheduled")
    void shouldReturnScheduledEnumWhenFromScheduled() {
        final var scheduleStatusEnum = ScheduleStatus.from("SCHEDULED");
        assertThat(scheduleStatusEnum).isEqualTo(ScheduleStatus.SCHEDULED);
    }

    @Test
    @DisplayName("Should ScheduleStatusNotFoundException when status not found")
    void shouldScheduleStatusNotFoundExceptionWhenNotFound() {
        assertThatExceptionOfType(ScheduleStatusNotFoundException.class)
                .isThrownBy(() -> ScheduleStatus.from("abc"))
                .withMessage("ScheduleStatus abc not found");
    }
}
