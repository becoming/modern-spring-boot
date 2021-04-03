package tech.becoming.modernspringboot.domain;

import org.mapstruct.Mapper;
import tech.becoming.modernspringboot.api.NewRobotDto;
import tech.becoming.modernspringboot.api.RobotDto;
import tech.becoming.modernspringboot.domain.Robot;

@Mapper(componentModel = "spring")
public interface RobotsMapper {

    RobotDto toDto(Robot robot);

    Robot toEntity(NewRobotDto dto);
}
