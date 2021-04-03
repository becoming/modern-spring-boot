package tech.becoming.modernspringboot.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RobotDto {

    private Long id;
    private String name;
    private String description;
    private String created;
    private String updated;

}
