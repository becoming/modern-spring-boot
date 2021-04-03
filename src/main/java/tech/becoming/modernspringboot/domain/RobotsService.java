package tech.becoming.modernspringboot.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.becoming.common.exceptions.NotFoundException;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RobotsService {

    private final RobotsRepository repository;
    private final RobotsHelper     helper;
    private final RobotsMapper     mapper;

    public List<RobotView> findInRange(int page, int size) {
        helper.validatePage(page, size);

        return repository
                .findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public RobotView findById(Long id) {
        helper.validateId(id);

        var robot = repository
                .findById(id)
                .orElseThrow(NotFoundException::new);

        return mapper.toDto(robot);
    }

    public RobotView create(NewRobotRequest dto) {
        helper.validate(dto);
        var robot = mapper.toEntity(dto);
        robot = setupNew(robot);
        robot = repository.save(robot);

        return mapper.toDto(robot);
    }

    public RobotView update(Long id, PatchRobotRequest dto) {
        var robot = repository
                .findById(id)
                .orElseThrow(NotFoundException::new);

        helper.validate(dto);
        robot = update(robot, dto);
        robot = repository.save(robot);

        return mapper.toDto(robot);
    }

    private Robot setupNew(Robot robot) {
        robot.setCreated(Instant.now());
        robot.setUpdated(Instant.now());
        robot.setInternalUid(UUID.randomUUID().toString());

        return robot;
    }

    private Robot update(Robot robot, PatchRobotRequest dto) {
        robot.setName(dto.getName());
        robot.setDescription(dto.getDescription());
        robot.setUpdated(Instant.now());

        return robot;
    }
}
