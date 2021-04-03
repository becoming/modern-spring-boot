package tech.becoming.modernspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.becoming.common.exceptions.NotFoundException;
import tech.becoming.modernspringboot.domain.RobotsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("robots")
@RequiredArgsConstructor
public class RobotsController {

    private final RobotsService service;
    private final RobotsMapper  mapper;

    @GetMapping
    public List<RobotDto> getRobots(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "50") int size) {

        return service.findInRange(page, size)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public RobotDto getRobot(@PathVariable Long id) {
        var robot = service.findById(id);

        return mapper.toDto(robot);
    }

    @GetMapping("{id}/optional")
    public RobotDto getOptionalRobot(@PathVariable Long id) {

        return service
                .findOptionalById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public RobotDto create(@RequestBody NewRobotDto dto) {
        var robot = service.create(dto);

        return mapper.toDto(robot);
    }

    @PutMapping("{id}")
    public RobotDto update(@PathVariable Long id,
                           @RequestBody PatchRobotDto dto) {
        var robot = service.update(id, dto);

        return mapper.toDto(robot);
    }

}
