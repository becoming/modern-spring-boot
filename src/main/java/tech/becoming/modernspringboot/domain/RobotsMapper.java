package tech.becoming.modernspringboot.domain;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RobotsMapper {

    RobotView toDto(Robot robot);

    Robot toEntity(NewRobotRequest dto);
}
