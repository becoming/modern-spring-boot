package tech.becoming.modernspringboot.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewRobotRequest {

    private String name;
    private String description;

}
