package tech.becoming.modernspringboot.domain;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RobotsService {

    private final RobotsRepository repository;
    private final RobotsHelper     helper;
    private final RobotsMapper     mapper;

    public Try<List<RobotView>> findInRange(int page, int size) {
        return Try.run(() -> helper.validatePage(page, size))
                .map($ -> PageRequest.of(page, size))
                .map(repository::findAll)
                .map(robots -> robots.getContent().stream())
                .map(stream -> stream.map(mapper::toDto))
                .map(stream -> stream.collect(Collectors.toList()))
                .onFailure(e -> log.error("Could not perform the find in range."));
    }

    public Try<RobotView> findById(Long id) {
        return Try.of(() -> id)
                .andThen(helper::validateId)
                .map($ -> repository.findById(id))
                .map(it -> it.orElseThrow(NotFoundException::new))
                .map(mapper::toDto)
                .onFailure(throwable -> log.error(throwable.getMessage()));
    }

    public Try<RobotView> create(NewRobotRequest dto) {
        return Try.run(() -> helper.validate(dto))
                .map($ -> mapper.toEntity(dto))
                .map(this::setupNew)
                .map(repository::save)
                .map(mapper::toDto);
    }

    public Try<RobotView> applyPatch(Long id, PatchRobotRequest dto) {
        return Try.run(() -> helper.validate(dto))
                .map($ -> repository.findById(id))
                .map(it -> it.orElseThrow(NotFoundException::new))
                .map(it -> applyPatch(it, dto))
                .map(repository::save)
                .map(mapper::toDto);
    }

    private Robot setupNew(Robot robot) {
        robot.setCreated(Instant.now());
        robot.setUpdated(Instant.now());
        robot.setInternalUid(UUID.randomUUID().toString());

        return robot;
    }

    private Robot applyPatch(Robot robot, PatchRobotRequest dto) {
        robot.setName(dto.getName());
        robot.setDescription(dto.getDescription());
        robot.setUpdated(Instant.now());

        return robot;
    }
}
