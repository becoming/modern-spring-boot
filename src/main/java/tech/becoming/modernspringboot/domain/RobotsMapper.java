package tech.becoming.modernspringboot.domain;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import tech.becoming.modernspringboot.domain.dto.NewRobotRequest;
import tech.becoming.modernspringboot.domain.dto.RobotView;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RobotsMapper {

    default List<RobotView> toDto(Page<Robot> page) {
        return page
                .map(this::toDto)
                .getContent();
    }

    RobotView toDto(Robot robot);

    Robot toEntity(NewRobotRequest dto);
}
