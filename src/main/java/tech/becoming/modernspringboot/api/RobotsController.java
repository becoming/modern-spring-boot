package tech.becoming.modernspringboot.api;

import io.vavr.API;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.becoming.common.exceptions.AbstractRuntimeException;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;
import tech.becoming.modernspringboot.domain.RobotsService;

import java.util.List;

@RestController
@RequestMapping("robots")
@RequiredArgsConstructor
public class RobotsController {

    private final RobotsService service;

    @GetMapping
    public ResponseEntity<List<RobotView>> getRobots(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "50") int size) {

        return service
                .findInRange(page, size)
                .map(ResponseEntity::ok)
                .get();
    }

    @GetMapping("{id}")
    public ResponseEntity<RobotView> getRobot(@PathVariable Long id) {
        return service
                .findById(id)
                .map(ResponseEntity::ok)
                .recover(AbstractRuntimeException.class, e -> ResponseEntity.status(e.getHttpCode()).build())
                .recover(Exception.class, e -> ResponseEntity.status(HttpStatus.IM_USED).build())
                .get();
    }

    @PostMapping
    public RobotView create(@RequestBody NewRobotRequest dto) {
        return service.create(dto);
    }

    @PutMapping("{id}")
    public RobotView update(@PathVariable Long id,
                            @RequestBody PatchRobotRequest dto) {
        return service.update(id, dto);
    }

}
