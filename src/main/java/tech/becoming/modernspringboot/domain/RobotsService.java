package tech.becoming.modernspringboot.domain;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import tech.becoming.common.exceptions.NotFoundException;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.PatchRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RobotsService {

    private final RobotsRepository repository;
    private final RobotsHelper     helper;
    private final RobotsMapper     mapper;

    public Try<List<RobotView>> findInRange(int page, int size) {
        return Try.run(() -> helper.validatePage(page, size))
                .map($ -> PageRequest.of(page, size))
                .map(repository::findAll)
                .map(Slice::getContent)
                .map(Collection::stream)
                .map(stream -> stream.map(mapper::toDto))
                .map(stream -> stream.collect(Collectors.toList()));
    }

    public Try<RobotView> findById(Long id) {
        return Try.of(() -> id)
                .andThen(helper::validateId)
                .mapTry($ -> repository.findById(id))
                .mapTry(Optional::get)
                .mapTry(mapper::toDto)
                .onFailure(throwable -> log.error(throwable.getMessage()));
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
