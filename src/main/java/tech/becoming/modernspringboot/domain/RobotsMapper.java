package tech.becoming.modernspringboot.domain;

import org.mapstruct.Mapper;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;

@Mapper(componentModel = "spring")
public interface RobotsMapper {

    RobotView toDto(Robot robot);

    Robot toEntity(NewRobotRequest dto);
}
