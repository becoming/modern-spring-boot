package tech.becoming.modernspringboot.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
