package com.renanfch.delibird.configuration;

import com.renanfch.delibird.core.exception.MessageServiceNotFoundException;
import com.renanfch.delibird.core.exception.ResponseError;
import com.renanfch.delibird.core.exception.ScheduleNotFoundException;
import com.renanfch.delibird.core.exception.ScheduleStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerConfig {

    @ExceptionHandler(MessageServiceNotFoundException.class)
    public @ResponseBody
    ResponseEntity<ResponseError> messageServiceNotFoundException(MessageServiceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public @ResponseBody
    ResponseEntity<ResponseError> scheduleNotFoundException(ScheduleNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(ScheduleStateException.class)
    public @ResponseBody
    ResponseEntity<ResponseError> scheduleStateException(ScheduleStateException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    ResponseEntity<ResponseError> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(e.getMessage()));
    }

}
