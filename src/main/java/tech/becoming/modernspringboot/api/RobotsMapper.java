package tech.becoming.modernspringboot.api;

import org.mapstruct.Mapper;
import tech.becoming.modernspringboot.domain.Robot;

@Mapper(componentModel = "spring")
public interface RobotsMapper {

    RobotDto toDto(Robot robot);

}
