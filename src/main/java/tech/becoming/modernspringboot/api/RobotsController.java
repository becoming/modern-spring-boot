package tech.becoming.modernspringboot.api;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tech.becoming.modernspringboot.domain.RobotsService;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;

import java.util.List;

@RestController
@RequestMapping("robots")
@RequiredArgsConstructor
@Slf4j
public class RobotsController {

    private final RobotsService    service;

    @GetMapping
    public Try<List<RobotView>> getRobots(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "50") int size) {
        return service
                .findInRange(PageRequest.of(page, size))
                .onFailure(e -> log.debug("Error occurred while finding in range, e: {}", e.getMessage()));
    }

    @GetMapping("{id}")
    public Try<RobotView> getRobot(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Try<RobotView> create(@RequestBody NewRobotRequest dto) {
        return service.create(dto);
    }

    @PutMapping("{id}")
    public Try<RobotView> update(@PathVariable Long id,
                                 @RequestBody PatchRobotRequest dto) {
        return service.update(id, dto);
    }

}
