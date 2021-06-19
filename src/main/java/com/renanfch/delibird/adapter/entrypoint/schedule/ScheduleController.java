package com.renanfch.delibird.adapter.entrypoint.schedule;

import com.renanfch.delibird.adapter.entrypoint.dto.ScheduleResponseDto;
import com.renanfch.delibird.adapter.entrypoint.mapper.ScheduleMapper;
import com.renanfch.delibird.adapter.entrypoint.dto.CreateScheduleDto;
import com.renanfch.delibird.adapter.entrypoint.dto.SearchScheduleDto;
import com.renanfch.delibird.adapter.entrypoint.mapper.SearchScheduleMapper;
import com.renanfch.delibird.core.usecase.RemoveScheduleUseCase;
import com.renanfch.delibird.core.usecase.ScheduleMessageUseCase;
import com.renanfch.delibird.core.usecase.SearchScheduleUseCase;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@Api(
        value = "REST API para consultar, agendar e remover mensagens",
        tags = {"API de agendamento de mensagens"}
)
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleMessageUseCase scheduleMessageUseCase;
    private final SearchScheduleUseCase searchScheduleUseCase;
    private final RemoveScheduleUseCase scheduleUseCase;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> register(@RequestBody @Valid final CreateScheduleDto createScheduleDto)  {
        final var createSchedule = ScheduleMapper.toEntity(createScheduleDto);
        final var scheduleMessage = scheduleMessageUseCase.create(createSchedule);
        final var createScheduleResponse = ScheduleMapper.toDtoResponse(scheduleMessage);

        return ResponseEntity.ok()
                .body(createScheduleResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> searchById(@PathVariable final int id)  {
        final var scheduledMessage = searchScheduleUseCase.getById(id);
        final var scheduleResponseDto = ScheduleMapper.toDtoResponse(scheduledMessage);

        return ResponseEntity.ok()
                .body(scheduleResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> search(final SearchScheduleDto searchScheduleDto) {
        final var searchSchedule = SearchScheduleMapper.toEntity(searchScheduleDto);
        final var scheduledsMessage = searchScheduleUseCase.getPage(searchSchedule);
        final var scheduledsResponseDto = scheduledsMessage.get()
                .map(ScheduleMapper::toDtoResponse)
                .collect(Collectors.toList());

        final var scheduledsPage = new PageImpl<>(scheduledsResponseDto, scheduledsMessage.getPageable(),scheduledsMessage.getTotalElements());

        return ResponseEntity.ok()
                .body(scheduledsPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final int id)  {
        scheduleUseCase.cancel(id);

        return ResponseEntity.noContent()
                .build();
    }
}
