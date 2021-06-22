package com.renanfch.delibird.adapter.entrypoint.schedule;

import com.renanfch.delibird.adapter.entrypoint.dto.CreateScheduleDto;
import com.renanfch.delibird.adapter.entrypoint.dto.ScheduleResponseDto;
import com.renanfch.delibird.adapter.entrypoint.mapper.ScheduleMapper;
import com.renanfch.delibird.core.exception.ResponseError;
import com.renanfch.delibird.core.usecase.RemoveScheduleUseCase;
import com.renanfch.delibird.core.usecase.ScheduleMessageUseCase;
import com.renanfch.delibird.core.usecase.SearchScheduleUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(
        value = "REST API for querying, scheduling and removing messages",
        tags = {"Message Scheduling API"}
)
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleMessageUseCase scheduleMessageUseCase;
    private final SearchScheduleUseCase searchScheduleUseCase;
    private final RemoveScheduleUseCase scheduleUseCase;

    @ApiOperation(value = "Message schedule record", response = ScheduleResponseDto.class, notes = "This operation records the message scheduling")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ScheduleResponseDto.class),
            @ApiResponse(code = 404, message = "It is not possible to create a schedule in the messageService", response = ResponseError.class),
            @ApiResponse(code = 400, message = "It is not possible to create a schedule in the state", response = ResponseError.class)})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScheduleResponseDto> register(@RequestBody @Valid final CreateScheduleDto createScheduleDto) {
        final var createSchedule = ScheduleMapper.toEntity(createScheduleDto);
        final var scheduleMessage = scheduleMessageUseCase.create(createSchedule);
        final var createScheduleResponse = ScheduleMapper.toDtoResponse(scheduleMessage);

        return ResponseEntity.ok()
                .body(createScheduleResponse);
    }

    @ApiOperation(value = "Message scheduling search", response = ScheduleResponseDto.class, notes = "This operation searches the message schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ScheduleResponseDto.class),
            @ApiResponse(code = 404, message = "Schedule is not found", response = ResponseError.class)})
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> searchById(@PathVariable final int id) {
        final var scheduledMessage = searchScheduleUseCase.getScheduleMessageById(id);
        final var scheduleResponseDto = ScheduleMapper.toDtoResponse(scheduledMessage);

        return ResponseEntity.ok()
                .body(scheduleResponseDto);
    }

    @ApiOperation(value = "Cancels message scheduling", notes = "This operation cancels the message sending schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Schedule is not found", response = ResponseError.class),
            @ApiResponse(code = 400, message = "It is not possible to delete a schedule because of state", response = ResponseError.class)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id) {
        scheduleUseCase.cancel(id);

        return ResponseEntity.noContent()
                .build();
    }
}
