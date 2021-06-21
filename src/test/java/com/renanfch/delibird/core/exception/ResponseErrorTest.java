package com.renanfch.delibird.core.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseErrorTest {

    @Test
    @DisplayName("Should return message")
    void shouldReturnMessage() {
        final var msgError = "fail";
        final var responseError = new ResponseError(msgError);

        assertThat(responseError.getMessage()).isEqualTo(msgError);
    }
}
