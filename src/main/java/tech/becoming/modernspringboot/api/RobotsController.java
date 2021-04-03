package tech.becoming.modernspringboot.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.becoming.modernspringboot.domain.RobotsService;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;

@RestController
@RequestMapping("robots")
@RequiredArgsConstructor
@Slf4j
public class RobotsController {

    private final RobotsService    service;
    private final ControllerHelper helper;

    @GetMapping
    public ResponseEntity<String> getRobots(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "50") int size) {
        return service
                .findInRange(page, size)
                .onFailure(e -> log.debug("Error occurred while finding in range, e: {}", e.getMessage()))
                .transform(helper::toJson);
    }

    @GetMapping("{id}")
    public ResponseEntity<String> getRobot(@PathVariable Long id) {
        return service.findById(id).transform(helper::toJson);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody NewRobotRequest dto) {
        return service.create(dto).transform(helper::toJson);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody PatchRobotRequest dto) {
        return service.update(id, dto).transform(helper::toJson);
    }

}
