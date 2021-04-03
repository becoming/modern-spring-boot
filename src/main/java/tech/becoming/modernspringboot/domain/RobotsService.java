package tech.becoming.modernspringboot.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.becoming.common.exceptions.NotFoundException;
import tech.becoming.modernspringboot.api.NewRobotDto;
import tech.becoming.modernspringboot.api.PatchRobotDto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RobotsService {

    private final RobotsRepository repository;
    private final RobotsHelper     helper;
    private final RobotsMapper     mapper;

    public List<Robot> findInRange(int page, int size) {
        helper.validatePage(page, size);

        return repository
                .findAll(PageRequest.of(page, size))
                .getContent();
    }

    public Robot findById(Long id) {
        helper.validateId(id);

        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Robot create(NewRobotDto dto) {
        helper.validate(dto);
        var robot = mapper.toEntity(dto);
        robot = setupNew(robot);

        return repository.save(robot);
    }

    public Robot update(Long id, PatchRobotDto dto) {
        var robot = findById(id);
        helper.validate(dto);
        robot = update(robot, dto);

        return repository.save(robot);
    }

    private Robot setupNew(Robot robot) {
        robot.setCreated(Instant.now());
        robot.setUpdated(Instant.now());
        robot.setInternalUid(UUID.randomUUID().toString());

        return robot;
    }

    private Robot update(Robot robot, PatchRobotDto dto) {
        robot.setName(dto.getName());
        robot.setDescription(dto.getDescription());
        robot.setUpdated(Instant.now());

        return robot;
    }
}
