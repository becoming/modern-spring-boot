package tech.becoming.modernspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public List<RobotView> getRobots(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "50") int size) {

        return service.findInRange(page, size);
    }

    @GetMapping("{id}")
    public RobotView getRobot(@PathVariable Long id) {
        return service.findById(id);
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
